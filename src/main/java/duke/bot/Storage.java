package duke.bot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import duke.exception.DukeLoadException;
import duke.exception.DukeSaveException;
import duke.exception.DukeTaskException;
import duke.task.Task;

/** An utility class that provide read/write related operations */
public class Storage {
    /** Directory path of the save file */
    private static final String FOLDER_NAME = "data";
    private static final String FILE_NAME = "duke.txt";
    private static final String PATH = FOLDER_NAME + "/" + FILE_NAME;

    private static void setupSaveFolder() {
        File folder = new File(FOLDER_NAME);
        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    private static void setupSaveFile() throws DukeSaveException {
        File dir = new File(FOLDER_NAME);
        assert (dir.exists() && dir.isDirectory());

        try {
            File file = new File(PATH);
            new PrintWriter(PATH).close();
        } catch (IOException e) {
            throw new DukeSaveException("Failed to setup save file");
        }
    }

    private static void writeTasksToSave(List<Task> tasks) throws DukeSaveException {
        try {
            FileWriter writer = new FileWriter(PATH);
            for (Task task : tasks) {
                writer.write(task.toSaveFileString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeSaveException("Failed to write to save file");
        }
    }

    /**
     * Saves all tasks in the current session into the hard disk
     *
     * @param tasks A list of tasks
     * @throws DukeSaveException if there is an issue writing into the hard disk
     */
    public static void saveTasks(List<Task> tasks) throws DukeSaveException {
        setupSaveFolder();
        setupSaveFile();
        writeTasksToSave(tasks);
    }


    private static void readTask(String entry, TaskManager manager) throws DukeLoadException {
        File dir = new File(FOLDER_NAME);
        assert (dir.exists() && dir.isDirectory());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy ha");
        String[] params = entry.split(" \\| ");

        Task task;

        try {
            if (params[0].equals("T")) {
                task = manager.addToDo(params[2]);
            } else if (params[0].equals("D")) {
                LocalDateTime due = LocalDateTime.parse(params[3], formatter);
                task = manager.addDeadline(params[2], due);
            } else if (params[0].equals("E")) {
                LocalDateTime start = LocalDateTime.parse(params[3], formatter);
                LocalDateTime end = LocalDateTime.parse(params[4], formatter);
                task = manager.addEvent(params[2], start, end);
            } else {
                throw new DukeLoadException("Invalid task type found: " + params[0]);
            }
        } catch (DukeTaskException e) {
            throw new DukeLoadException(e.getMessage());
        }

        boolean isDone = params[1].equals("1");
        if (isDone) {
            task.markAsDone();
        }
    }

    private static void readSaveTo(TaskManager manager) throws DukeLoadException {
        try {
            File file = new File(PATH);

            if (!file.createNewFile()) {
                Scanner scanner = new Scanner(file);

                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    readTask(line, manager);
                }
            }
        } catch (IOException e) {
            throw new DukeLoadException("Issue with IO while loading tasks");
        } catch (DukeLoadException e) {
            throw new DukeLoadException(e.getMessage());
        }
    }

    /**
     * Loads tasks from the hard disk into current session's task list
     *
     * @param manager TaskManager that contains the current session's task list
     * @throws DukeLoadException if there is an issue reading tasks from the hard disk
     */
    public static void loadTasks(TaskManager manager) throws DukeLoadException {
        setupSaveFolder();
        readSaveTo(manager);
    }
}

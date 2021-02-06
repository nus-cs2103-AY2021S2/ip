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
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

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
    public static void save(List<Task> tasks) throws DukeSaveException {
        setupSaveFolder();
        setupSaveFile();
        writeTasksToSave(tasks);
    }

    /**
     * Loads tasks from the hard disk into current session's task list
     *
     * @param taskManager TaskManager that contains the current session's task list
     * @throws DukeLoadException if there is an issue reading tasks from the hard disk
     */
    public static void loadTasksTo(TaskManager taskManager) throws DukeLoadException {
        setupSaveFolder();

        // Load the save file or create one if missing
        File file = new File(PATH);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy ha");

        try {
            // If the save file already exists, load its tasks
            if (!file.createNewFile()) {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String[] splits = scanner.nextLine().split(" \\| ");
                    boolean isDone = splits[1].equals("1");

                    switch (splits[0]) {
                    case "T":
                        ToDo toDo = taskManager.addToDo(splits[2]);
                        if (isDone) {
                            toDo.markAsDone();
                        }
                        break;
                    case "D":
                        LocalDateTime dateTime = LocalDateTime.parse(splits[3], formatter);
                        Deadline deadline = taskManager.addDeadline(splits[2], dateTime);
                        if (isDone) {
                            deadline.markAsDone();
                        }
                        break;
                    case "E":
                        LocalDateTime start = LocalDateTime.parse(splits[3], formatter);
                        LocalDateTime end = LocalDateTime.parse(splits[4], formatter);
                        Event event = taskManager.addEvent(splits[2], start, end);
                        if (isDone) {
                            event.markAsDone();
                        }
                        break;
                    default:
                        throw new DukeLoadException("Invalid task type found: " + splits[0]);
                    }
                }
            }
        } catch (IOException e) {
            throw new DukeLoadException("Issue with IO while loading tasks");
        } catch (DukeTaskException e) {
            throw new DukeLoadException(e.getMessage());
        }
    }
}

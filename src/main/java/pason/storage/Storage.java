package pason.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pason.exceptions.PasonException;
import pason.tasks.Deadline;
import pason.tasks.Event;
import pason.tasks.Task;
import pason.tasks.ToDo;

/**
 * Storage class.
 * Handles the storing and loading of tasks from file.
 */
public class Storage {
    private static final String FILE_DIRECTORY = "data";
    private static final String FILE_NAME = "tasks.txt";

    /**
     * Initialises the Storage class.
     */
    public Storage() {
    }

    /**
     * Loads stored tasks from file.
     * If valid tasks exist, add to tasks.
     *
     * @throws FileNotFoundException  If file directory or name is not found
     * @throws PasonException  If the task list contains incorrect format
     */
    public List<Task> loadTasks() throws FileNotFoundException, PasonException {
        File directory = new File(FILE_DIRECTORY);
        File file = new File(FILE_DIRECTORY + "/" + FILE_NAME);
        if (!directory.exists()) {
            directory.mkdir();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new PasonException(e.getMessage());
            }
        }
        assert file.exists() : "File should be readable.";
        List<Task> tasks = new ArrayList<>();
        Scanner s = new Scanner(file);
        Task task;
        int failedImports = 0;
        while (s.hasNext()) {
            task = parseFileEntry(s.nextLine());
            if (task != null) {
                tasks.add(task);
            } else {
                failedImports++;
            }
        }
        if (failedImports > 0) {
            throw new PasonException("There was a problem parsing "
                    + failedImports + " task(s) from the file.");
        }
        return tasks;
    }
    /**
     * Parses the given task entry String.
     * Returns null if given string is in incorrect format.
     *
     * @param text  Text entry to be parsed.
     * @return Task object parsed from text.
     */
    public static Task parseFileEntry(String text) {
        String[] inputParts = text.split(" \\| ");
        if (inputParts[0].equals("T") && inputParts.length == 3) {
            ToDo newToDo = new ToDo(inputParts[2]);
            if (inputParts[1].equals("1")) {
                newToDo.markAsDone();
            }
            return newToDo;
        } else if (inputParts[0].equals("E") && inputParts.length == 4) {
            String[] eventDate = inputParts[3].split(" ");
            Event newEvent = new Event(inputParts[2],
                    LocalDate.parse(eventDate[0]), (eventDate.length == 1 ? null : eventDate[1]));
            if (inputParts[1].equals("1")) {
                newEvent.markAsDone();
            }
            return newEvent;
        } else if (inputParts[0].equals("D") && inputParts.length == 4) {
            Deadline newDeadline = new Deadline(inputParts[2],
                    LocalDateTime.parse(inputParts[3]));
            if (inputParts[1].equals("1")) {
                newDeadline.markAsDone();
            }
            return newDeadline;
        } else {
            return null;
        }
    }

    /**
     * Saves all tasks back to file.
     * Uses .toFileFormat() to parse Task.
     * Overrides current file.
     *
     * @param tasks  Tasks list to retrieve tasks from.
     * @throws IOException  If target file not found.
     */
    public void saveAllTasks(List<Task> tasks) throws IOException {
        assert tasks != null : "Tasks cannot be null.";
        try {
            FileWriter fw = new FileWriter(FILE_DIRECTORY
                    + "/" + FILE_NAME);
            for (int i = 0; i < tasks.size(); i++) {
                fw.write(tasks.get(i).toFileFormat() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * Appends given task to file.
     * Uses .toFileFormat() to parse Task.
     * Does not override existing file.
     *
     * @param task Task to store to file.
     * @throws IOException  If target file not found.
     */
    public void appendTask(Task task) throws IOException {
        assert task != null : "Task cannot be null.";
        try {
            FileWriter fw = new FileWriter(FILE_DIRECTORY
                    + "/" + FILE_NAME, true);
            fw.write(task.toFileFormat() + "\n");
            fw.close();
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }
}

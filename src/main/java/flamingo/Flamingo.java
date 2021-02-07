package flamingo;

import java.io.FileNotFoundException;

/**
 * Represents the chat bot.
 */
public class Flamingo {
    private static Storage storage = new Storage();
    private static TaskList tasks = new TaskList();
    private static ArchivedTaskList archivedTasks = new ArchivedTaskList();

    /**
     * Loads the existing save data if the files exist.
     */
    public Flamingo() {
        loadArchive();
        loadTasks();
    }

    public static Storage getStorage() {
        return storage;
    }

    public static TaskList getTasks() {
        return tasks;
    }

    public static ArchivedTaskList getArchivedTasks() {
        return archivedTasks;
    }

    /**
     * Loads tasks if the data text file exists.
     * Else, create a new Task List.
     */
    private void loadTasks() {
        try {
            tasks = new TaskList(storage.loadData());
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        }
    }

    private void loadArchive() {
        try {
            archivedTasks = new ArchivedTaskList(storage.loadArchive());
        } catch (FileNotFoundException e) {
            archivedTasks = new ArchivedTaskList();
        }
    }

    /**
     * Generates a response.
     */
    public static String getResponse(String input) {
        return Parser.run(input);
    }
}

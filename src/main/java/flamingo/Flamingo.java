package flamingo;

import java.io.FileNotFoundException;

/**
 * Represents the chat bot.
 */
public class Flamingo {
    private static Storage storage = new Storage();
    private static TaskList tasks;

    public Flamingo() {
        loadTasks();
    }

    public static Storage getStorage() {
        return storage;
    }

    public static TaskList getTasks() {
        return tasks;
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

    /**
     * Generates a response.
     */
    public static String getResponse(String input) {
        return Parser.run(input);
    }
}

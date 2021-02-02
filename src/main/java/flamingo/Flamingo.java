package flamingo;

import java.io.FileNotFoundException;

/**
 * Represents the chat bot.
 */
public class Flamingo {
    private static Storage storage = new Storage();
    private static TaskList tasks;

    public Flamingo() {
        try {
            tasks = new TaskList(storage.loadData());
        } catch (FileNotFoundException e) {
            Ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static Storage getStorage() {
        return storage;
    }

    public static TaskList getTasks() {
        return tasks;
    }

    /**
     * Generates a response.
     */
    public static String getResponse(String input) {
        String response = Parser.run(input);
        return response;
    }
}

package jeff;

import jeff.command.Command;

/**
 * Main class for the Jeff chatbot.
 */
public class Jeff {
    private final Storage storage;
    private TaskList tasks;

    /**
     * Constructor for the Jeff class.
     * Initializes the Storage, TaskList and Ui needed for the chatbot.
     *
     * @param filePath File path to load/store tasks from.
     */
    public Jeff(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (JeffException e) {
            tasks = new TaskList();
        }
        assert tasks instanceof TaskList : "TaskList is not present";
    }

    public String getResponse (String message) {
        try {
            Command c = Parser.parse(message);
            return c.execute(tasks, storage);
            //return Parser.execute(message, tasks, storage);
        } catch (JeffException e) {
            return e.getMessage();
        }
    }
}

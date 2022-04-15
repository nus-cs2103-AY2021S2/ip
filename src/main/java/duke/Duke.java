package duke;

/**
 * Encompasses the behavior of the Duke chat-bot. Duke allows users to create
 * different types of tasks in a todo-list, and can do various functions on the
 * list such as deleting and listing. The list is saved by the Storage object at
 * "./data/duke.txt".
 */
public class Duke {
    private final Parser parser;

    /**
     * Starts Duke with instance of Ui, Storage, TaskList, and Parser to receive
     * input and serve output.
     */
    public Duke() {
        Storage storage = Storage.createStorage("./data/duke.txt");
        TaskList tasks = new TaskList();
        if (storage != null) {
            storage.loadTaskList(tasks);
        }

        parser = new Parser(tasks, storage);
    }

    public String showWelcome() {
        return Ui.showWelcome();
    }

    /**
     * Returns a response to the input given by parsing the input.
     *
     * @param input User input into text field of Duke.
     * @return string response.
     */
    public String getResponse(String input) {
        return parser.parseCommand(input);
    }
}

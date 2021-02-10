/**
 * Driver class. Creating a Duke object runs the application.
 */
public class Duke {
    private final Parser parser;

    /**
     * Duke constructor specifying the file path to save the task list to.
     * Creates and stores a reference to a Parser.
     */
    public Duke() {
        Storage storage = new Storage("data/tasks.txt");
        TaskList taskList = new TaskList();
        storage.readFromStorage(taskList);
        parser = new Parser(taskList, storage);
    }

    public String getResponse(String input) {
        return parser.parse(input);
    }
}

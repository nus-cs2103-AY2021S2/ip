/**
 * Duke keeps track of a list of tasks.
 */
public class Duke {

    /** Storage that controls saving and reading file. */
    private Storage storage;

    /** Parser that processes commands. */
    private Parser parser;

    /** List of tasks. */
    private TaskList tasks;

    /**
     * Initializes a newly created Duke object with empty task list, storage and parser.
     */
    public Duke() {
        tasks = new TaskList();
        storage = new Storage(tasks);
        parser = new Parser(tasks);
    }

    /**
     * Returns the storage of this Duke object.
     *
     * @return Storage object of this Duke object.
     */
    public Storage getStorage() {
        return this.storage;
    }

    /**
     * Returns the parser of this Duke object.
     *
     * @return Parser object of this Duke object.
     */
    public Parser getParser() {
        return this.parser;
    }

    /**
     * Generate a response to user input.
     *
     * @return String that represents the response to user input.
     */
    public String getResponse(String input, Duke duke) {
        String processedString = duke.parser.processCommand(input);
        return processedString;
    }
}

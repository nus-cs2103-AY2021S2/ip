package duke;

public class Duke {

    private static final String DUKE_LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    protected final TaskList tasks;
    protected final Storage storage;
    protected final Parser parser;
    protected final Ui ui;

    /**
     * Instantiates the main duke class
     */
    public Duke() {
        tasks = new TaskList();
        storage = new Storage("data/duke.txt");
        parser = new Parser();
        ui = new Ui(tasks, DUKE_LOGO);
        storage.readTasks(tasks);
    }

    /**
     * Generate a response to user input.
     */
    public String getResponse(String input) {
        String response = parser.processLine(input, tasks);
        storage.saveTasks(tasks);
        return response;
    }
}

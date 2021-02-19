public class Duke {

    protected static final String PATH = "data/tasks.txt";

    protected Parser parser;
    protected Storage storage;
    protected TaskList tasks;
    protected Ui ui;

    /**
     * Creates a new instance of Duke.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(PATH);
        storage.checkFileExistence();
        tasks = new TaskList(storage.loadTasks());
        parser = new Parser(storage, tasks, ui);
    }
}

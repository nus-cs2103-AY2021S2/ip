public class Duke {

    static final String PATH = "data/tasks.txt";

    protected Parser parser;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

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

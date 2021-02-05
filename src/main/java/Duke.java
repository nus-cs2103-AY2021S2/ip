public class Duke {

    protected Parser parser;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a new instance of Duke.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/tasks.txt");
        storage.checkFileExistence();
        tasks = new TaskList(storage.loadTasks());
        parser = new Parser(storage, tasks, ui);
    }
}

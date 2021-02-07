public class Duke {

    private final Storage storage;
    private final Statistics stat;
    private final Ui ui;
    private TaskList tasks;

    /**
     * Constructor to set up Duke with previously stored file.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList();
        stat = new Statistics();
    }

    /**
     * Duke welcome the user.
     */
    public String welcome() {
        ui.showWelcome();
        return ui.getMessage();
    }

    /**
     * Load the file into storage and list out the current tasks.
     *
     * @return loaded.
     */
    public String loading() {
        try {
            tasks = new TaskList(storage.load(stat));
            ui.showList(tasks.listTask());
            return ui.getMessage();
        } catch (DukeException e) {
            tasks = new TaskList();
            return e.getMessage();
        }
    }

    /**
     * Command is created by parsing the input and then executed.
     * Error message is shown if there is DukeException.
     *
     * @param input user input.
     * @return response.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parseInput(input);
            c.execute(tasks, ui, storage, stat);
        } catch (DukeException e) {
            ui.showError(e);
        }
        return ui.getMessage();
    }

}

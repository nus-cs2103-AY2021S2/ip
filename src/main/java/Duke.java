
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor to set up Duke with previously stored file.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("Duke.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Command is created by parsing the input and then executed.
     * Error message is shown if there is DukeException.
     *
     * @param input
     * @return
     */
    public String getResponse(String input) {
        String respone;
        try {
            Command c = Parser.getCommand(input);
            respone = c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            respone = ui.showError(e.getMessage());
        }
        return respone;
    }

}

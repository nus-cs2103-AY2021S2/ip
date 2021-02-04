
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
     * @param input user input.
     * @return response.
     */
    public String getResponse(String input) {
        String response;
        try {
            Command c = Parser.getCommand(input);
            response = c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            response = ui.showError(e.getMessage());
        }
        return response;
    }

}

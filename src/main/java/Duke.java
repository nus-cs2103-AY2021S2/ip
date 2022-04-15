import java.io.IOException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a Duke object.
     *
     * @param filePath String that shows the filepath to the file that stores past data.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }


    /**
     * Gets the response for the user request.
     *
     * @param input A string containing the user's request.
     * @return String response based on the request made.
     */
    String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException | IOException e) {
            return ui.showError(e.getMessage());
        }
    }

}

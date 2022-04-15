package duke;

import helper.DukeException;
import helper.Parser;
import helper.Storage;
import helper.TaskList;
import helper.Ui;
import helper.command.Command;


/**
 * Main class
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private boolean isExit = false;

    /**
     * Initializing duke
     * @param filePath
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.showError());
            tasks = new TaskList();
        }
        parser = new Parser();
    }


    public String getResponse(String input) {
        String response = "";
        assert response.equals("");
        try {
            Command c = parser.parse(input);
            response = c.execute(tasks, ui, storage);
            isExit = c.isExit();
        } catch (DukeException e) {
            response = ui.showError(e.getMessage());
        }
        return response;
    }

    public boolean getIsExit() {
        return isExit;
    }

}

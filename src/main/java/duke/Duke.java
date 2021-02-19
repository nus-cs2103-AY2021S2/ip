package duke;

import duke.command.Command;
import duke.command.Statement;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

public class Duke {

    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    /**
     * Returns a Duke object representing the chatbox.
     *
     * @param filePath The filepath storing the data.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Initialize the program by start taking in inputs.
     */
    public String getResponse(String input) {
        try {
            Statement statement = new Statement(input);
            Command c = statement.parse();
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }
}


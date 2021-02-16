package duke;

import java.io.IOException;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Main class for programme to run.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/data.txt");
        try {
            tasks = storage.loadData();
        } catch (IOException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Gets response from user
     *
     * @param input user's response
     * @return Duke's reply
     */
    String getResponse(String input) {
        ui.showWelcome();
        try {
            Command command = Parser.parse(input);
            boolean isExit = command.isExit();
            if (isExit) {
                return ui.exitDuke();
            }
            return command.execute(tasks, input, storage);

        } catch (DukeException | IOException e) {
            return e.getMessage();
        }
    }
}

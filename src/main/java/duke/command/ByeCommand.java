package duke.command;

import duke.DukeException;
import duke.Main;
import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

public class ByeCommand implements Command {
    private Ui ui;

    /**
     * Create and initialize a Bye Command.
     *
     * @param fullCmd The full user input in String form.
     * @param ui The ui object responsible for displaying bye messages to the CLI.
     */
    public ByeCommand(String fullCmd, Ui ui) {
        this.ui = ui;
    }

    @Override
    public String run(Storage storage, TaskList taskList) throws DukeException {
        Main.exit();
        return ui.returnBye();
    }
}

package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Handles the Exit command of the user to allow user to exit the command line interface.
 * Format of command: "bye".
 */
public class ExitCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printIndentOutput("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

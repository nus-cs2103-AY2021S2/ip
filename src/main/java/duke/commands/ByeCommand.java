package duke.commands;

import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Handles the logic of terminating the application.
 */
public class ByeCommand extends Command {

    /**
     * Prints responses to notify the users that the application is terminating.
     *
     * @param tasks A collection of <code>Task</code> objects representing the application's state.
     * @param ui A handler to manage the application's user-interface layer.
     */
    public void execute(TaskList tasks, Ui ui) {
        ui.handleBye();
    }

    public boolean isExit() {
        return true;
    }
}

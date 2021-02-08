package duke.commands;

import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Dummy class to use when nothing is to be done.
 */
public class DoNothingCommand extends Command {

    /**
     * Does nothing.
     *
     * @param tasks A collection of <code>Task</code> objects representing the application's state.
     * @param ui A handler to manage the application's user-interface layer.
     */
    public void execute(TaskList tasks, Ui ui) {
    }

    public boolean isExit() {
        return false;
    }
}

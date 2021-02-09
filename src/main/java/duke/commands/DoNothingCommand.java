package duke.commands;

import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Handles the logic for when the users input invalid commands
 */
public class DoNothingCommand extends Command {
    private final String exceptionMessage;

    public DoNothingCommand(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    /**
     * Does nothing.
     *
     * @param tasks A collection of <code>Task</code> objects representing the application's state.
     */
    public void execute(TaskList tasks) {
    }

    /**
     * Computes a feedback or response to the users' input.
     *
     * @param tasks A collection of <code>Task</code> objects representing the application's state.
     * @param ui    A handler to manage the application's user-interface layer.
     * @return Error message
     */
    public String getResponse(TaskList tasks, Ui ui) {
        return this.exceptionMessage;
    }

    /**
     * Returns false as invalid user input should not terminate the application.
     *
     * @return false
     */
    public boolean isExit() {
        return false;
    }
}

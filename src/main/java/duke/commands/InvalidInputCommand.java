package duke.commands;

import duke.tasks.TaskList;

/**
 * Handles the logic for when the users input invalid commands
 */
public class InvalidInputCommand extends Command {
    private final String message;

    public InvalidInputCommand(String message) {
        this.message = message;
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
     * @return Error message
     */
    public String getResponse(TaskList tasks) {
        return this.message;
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

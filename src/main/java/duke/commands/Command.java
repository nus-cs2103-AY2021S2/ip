package duke.commands;

import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Handles the logic of the application.
 */
public abstract class Command {

    /**
     * Alters the application's state through the input <code>TaskList</code>.
     *
     * @param tasks A collection of <code>Task</code> objects representing the application's state.
     */
    public abstract void execute(TaskList tasks);

    /**
     * Computes a feedback or response to the users' input.
     *
     * @param tasks A collection of <code>Task</code> objects representing the application's state.
     * @param ui    A handler to manage the application's user-interface layer.
     * @return A <code>String</code> of response.
     */
    public abstract String getResponse(TaskList tasks, Ui ui);

    /**
     * Determines whether or not to terminate the application.
     *
     * @return True if the application should terminate, and false otherwise.
     */
    public abstract boolean isExit();
}

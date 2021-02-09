package duke.commands;

import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Handles the logic of listing the tasks in the to-do list.
 */
public class ListCommand extends Command {

    /**
     * Does nothing.
     *
     * @param tasks A collection of <code>Task</code> objects representing the application's state.
     */
    public void execute(TaskList tasks) {
    }

    /**
     * Returns false as searching for tasks should not terminate the application.
     *
     * @return false
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Creates a response to display the existing tasks to the users. The output will
     * take the form of a multi-line string, with each row displaying one task with its
     * index, description and status.
     *
     * @param tasks A collection of <code>Task</code> objects representing the application's state.
     * @param ui    A handler to manage the application's user-interface layer.
     * @return A <code>String</code> displaying the existing tasks.
     */
    public String getResponse(TaskList tasks, Ui ui) {
        return ui.handleList(tasks);
    }
}

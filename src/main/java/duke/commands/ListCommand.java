package duke.commands;

import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Handles the logic of listing the tasks in the to-do list.
 */
public class ListCommand extends Command {

    /**
     * Prints the descriptions and statuses of the <code>Task</code> objects in the input
     * <code>TaskList</code> collection.
     *
     * @param tasks A collection of <code>Task</code> objects representing the application's state.
     * @param ui A handler to manage the application's user-interface layer.
     */
    public void execute(TaskList tasks, Ui ui) {
        ui.handleList(tasks);
    }

    public boolean isExit() {
        return false;
    }
}

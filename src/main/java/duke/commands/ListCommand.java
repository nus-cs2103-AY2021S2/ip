package duke.commands;

import duke.tasks.TaskList;
import duke.ui.TaskStringFormatter;

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
     * Returns false as listing tasks should not terminate the application.
     *
     * @return false
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Creates a response to display the existing tasks, if any, to the users. The output will
     * take the form of a multi-line string, with each row displaying one task with its
     * index, description and status.
     *
     * @param tasks A collection of <code>Task</code> objects representing the application's state.
     * @return A <code>String</code> displaying the existing tasks.
     */
    public String getResponse(TaskList tasks) {
        if (tasks.getSize() == 0) {
            return "You have no tasks in your list yet :)";
        } else {
            return "Here are the task(s) in your list:\n"
                    + "\n"
                    + TaskStringFormatter.getTaskTable(tasks);
        }
    }
}

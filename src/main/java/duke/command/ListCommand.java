package duke.command;

import duke.common.DukeString;
import duke.task.TaskList;

/**
 * Represents a command that lists the given tasks in the TaskList.
 */
public class ListCommand implements Command {
    /**
     * Formats the task list into a human readable format
     *
     * @param taskList the task list to be read by the command.
     * @return a formatted version of the task list, as a String.
     */
    @Override
    public String execute(final TaskList taskList) {
        if (taskList.getSize() != 0) {
            return DukeString.MESSAGE_LIST + taskList.toString();
        }
        return DukeString.MESSAGE_LIST_EMPTY;
    }
}

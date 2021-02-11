package duke.command;

import duke.common.DukeException;
import duke.common.DukeString;
import duke.task.TaskList;

/**
 * Represents a command that deletes a given Task from a given TaskList.
 */
public class DeleteCommand implements Command {
    private final int index;

    /**
     * Constructs a new delete command with the specified index.
     *
     * @param idx the index of the task to be deleted.
     */
    public DeleteCommand(final int idx) {
        this.index = idx;
    }

    /**
     * Deletes the task associated with the index, if valid.
     *
     * @param taskList the task list to be modified by the command.
     * @return a formatted message to be output to the user.
     */
    @Override
    public String execute(final TaskList taskList) {
        if (index > taskList.getSize() || index <= 0) {
            throw new DukeException.InvalidTask();
        }
        return String.format(DukeString.MESSAGE_DELETE, taskList.deleteTask(index), taskList.getSize());
    }
}

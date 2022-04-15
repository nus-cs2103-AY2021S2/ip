package duke.command;

import duke.common.DukeException;
import duke.common.DukeString;
import duke.task.TaskList;

/**
 * Represents a command that marks a given Task as done from a given TaskList.
 */
public class DoneCommand implements Command {
    private final int index;

    /**
     * Constructs a new done command with the specified index.
     *
     * @param idx the index of the task to be marked as done.
     */
    public DoneCommand(final int idx) {
        this.index = idx;
    }

    /**
     * Marks the task associated with the index as done, if valid.
     *
     * @param taskList the task list to be modified by the command.
     * @return a formatted message to be output to the user.
     */
    @Override
    public String execute(final TaskList taskList) {
        if (index > taskList.getSize() || index <= 0) {
            throw new DukeException.InvalidTask();
        }
        return String.format("%s\n%s", DukeString.MESSAGE_DONE, taskList.markTaskDone(index));
    }
}

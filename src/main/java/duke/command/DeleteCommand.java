package duke.command;

import duke.common.DukeException;
import duke.common.DukeString;
import duke.task.TaskList;

public class DeleteCommand implements Command {
    private final int index;

    public DeleteCommand(final int idx) {
        this.index = idx;
    }

    @Override
    public String execute(final TaskList taskList) {
        if (index > taskList.size()) {
            throw new DukeException.InvalidTask();
        }
        return String.format(DukeString.MESSAGE_DELETE, taskList.deleteTask(index), taskList.size());
    }
}

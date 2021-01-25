package duke.command;

import duke.common.DukeString;
import duke.task.Task;
import duke.task.TaskList;

public abstract class AddCommand implements Command {
    private final Task task;

    public AddCommand(final Task task) {
        this.task = task;
    }

    @Override
    public String execute(final TaskList taskList) {
        taskList.addTask(task);
        return String.format(DukeString.MESSAGE_ADDED, task.toString(), taskList.size());
    }
}

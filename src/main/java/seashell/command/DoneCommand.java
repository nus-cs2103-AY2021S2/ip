package seashell.command;

import seashell.TaskList;
import seashell.exception.SeashellException;
import seashell.task.Task;

public class DoneCommand implements Command {

    private final int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList) throws SeashellException {
        if (taskList.size() < index) {
            throw new SeashellException("OOPS!!! Task index is out of bounds!");
        } else {
            Task updated = taskList.get(index - 1).setDone();
            taskList.set(index - 1, updated);
            return "Nice! I've marked this task as done: \n" + updated;
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return this == other
                || (other instanceof DoneCommand
                && this.index == (((DoneCommand) other).index));
    }
}

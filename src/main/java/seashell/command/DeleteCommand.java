package seashell.command;

import seashell.TaskList;
import seashell.exception.SeashellException;
import seashell.task.Task;

public class DeleteCommand implements Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList) throws SeashellException {
        if (taskList.size() < index) {
            throw new SeashellException("OOPS!!! Task index is out of bounds!");
        } else {
            Task toRemove = taskList.remove(index - 1);
            return "Noted. I have removed " + toRemove + "\n You now have "
                    + taskList.size() + "items in the task list";
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return this == other
                || (other instanceof DeleteCommand
                && this.index == (((DeleteCommand) other).index));
    }
}

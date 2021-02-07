package seashell.command;

import seashell.SaveHandler;
import seashell.SeashellException;
import seashell.TaskList;
import seashell.task.Task;

public class DeleteCommand implements Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskListObj, SaveHandler saveHandler) throws SeashellException {
        if (taskListObj.taskList.size() < index) {
            throw new SeashellException("OOPS!!! Task index is out of bounds!");
        } else {
            Task toRemove = taskListObj.taskList.remove(index - 1);
            // taskListObj = new TaskList(taskListObj.taskList);
            saveHandler.updateSaveFile(taskListObj.taskList);
            return "Noted. I have removed " + toRemove + "\n You now have "
                    + taskListObj.taskList.size() + "items in the task list";
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

package seashell.command;

import seashell.SaveHandler;
import seashell.SeashellException;
import seashell.task.Task;
import seashell.TaskList;

public class DoneCommand implements Command {

    private final int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskListObj, SaveHandler saveHandler) throws SeashellException {
        if (taskListObj.taskList.size() < index) {
            throw new SeashellException("OOPS!!! Task index is out of bounds!");
        } else {
            Task updated = taskListObj.taskList.get(index - 1).setDone();
            taskListObj.taskList.set(index - 1, updated);
//            taskListObj = new TaskList(taskListObj.taskList);
            saveHandler.updateSaveFile(taskListObj.taskList);
            return "Nice! I've marked this task as done: \n" + updated;
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

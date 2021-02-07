package seashell.command;

import seashell.SaveHandler;
import seashell.SeashellException;
import seashell.TaskList;
import seashell.task.Task;

public class ListCommand implements Command {

    @Override
    public String execute(TaskList taskListObj, SaveHandler saveHandler) throws SeashellException {
        if (taskListObj.taskList.isEmpty()) {
            throw new SeashellException("OOPS!!! Task list is currently still empty!");
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= taskListObj.taskList.size(); i++) {
                Task t = taskListObj.taskList.get(i - 1);
                sb.append(i + ". " + t + "\n");
            }
            return sb.toString();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

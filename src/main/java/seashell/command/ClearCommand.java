package seashell.command;

import seashell.SaveHandler;
import seashell.SeashellException;
import seashell.TaskList;

import java.util.ArrayList;

public class ClearCommand implements Command {
    @Override
    public String execute(TaskList taskListObj, SaveHandler saveHandler) throws SeashellException {
        if (taskListObj.taskList.isEmpty()) {
            throw new SeashellException("OOPS!!! Task list is already empty!");
        } else {
            saveHandler.clearSaveFile();
            taskListObj.taskList = new ArrayList<>();
            return "Task list has been cleared!";
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

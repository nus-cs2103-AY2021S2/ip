package seashell.command;

import seashell.storage.SaveHandler;
import seashell.exception.SeashellException;
import seashell.TaskList;

import java.util.ArrayList;

public class ClearCommand implements Command {
    @Override
    public String execute(TaskList taskList, SaveHandler saveHandler) throws SeashellException {
        if (taskList.isEmpty()) {
            throw new SeashellException("OOPS!!! Task list is already empty!");
        } else {
            saveHandler.clearSaveFile();
            taskList.clear();
            return "Task list has been cleared!";
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

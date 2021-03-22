package seashell.command;

import seashell.TaskList;
import seashell.exception.SeashellException;
import seashell.storage.SaveHandler;

public interface Command {

    /**
     * Executes the command and returns the result.
     */
    String execute(TaskList taskList, SaveHandler saveHandler) throws SeashellException;

    boolean isExit();
}

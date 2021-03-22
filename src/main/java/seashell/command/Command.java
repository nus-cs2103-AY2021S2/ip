package seashell.command;

import seashell.TaskList;
import seashell.exception.SeashellException;

public interface Command {

    /**
     * Executes the command and returns the result.
     */
    String execute(TaskList taskList) throws SeashellException;

    boolean isExit();
}

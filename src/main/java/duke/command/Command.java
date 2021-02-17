package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
/**
 * It is a super class that named Command for the Duke program.
 * When the parser calls it, it receives the requests from the users
 * during the running of the program and starts to call its subclasses for
 * execution.
 */
public class Command {
    protected String userMessage;
    protected static boolean isExit;

    public Command(String userMessage) {
        this.userMessage = userMessage;
        isExit = false;
    }

    public boolean isExit() {
        return isExit;
    }

    public String execute(TaskList taskList) throws DukeException {
        return "";
    }

}

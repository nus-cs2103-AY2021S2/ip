package duke.command;

import duke.ui.Ui;
import duke.exception.DukeException;
import duke.task.TaskList;
/**
 * It is a super class that named Command for the Duke program.
 * When the parser calls it, it will receive the requests from the users
 * during the running of the program and starts to call its subclasses for
 * execution.
 */
public class Command {
    protected String userMessage;
    protected static boolean exit;

    public Command(String userMessage) {
        this.userMessage = userMessage;
        exit = false;
    }

    public boolean isExit() {
        return exit;
    }

    public void execute(TaskList taskList, Ui ui) throws DukeException {}

}

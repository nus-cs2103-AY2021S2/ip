package duke.command;

import duke.ui.Ui;
import duke.exception.DukeException;
import duke.task.TaskList;

public class Command {
    protected String userMessage;
    protected static boolean exit;

    public Command(String userMessage){
        this.userMessage = userMessage;
        exit = false;
    }

    public boolean isExit(){
        return exit;
    }

    public void execute(TaskList taskList, Ui ui) throws DukeException {
        return;
    }

}

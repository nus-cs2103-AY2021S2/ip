package duke.command;
import duke.exception.DukeException;
import duke.ui.Ui;
import duke.task.TaskList;

import java.util.LinkedList;

public class ByeCommand extends Command{

    public ByeCommand(String userMessage){
        super(userMessage);
    }

    public void execute(TaskList taskList, Ui ui){
        exit = true;
        ui.display("Bye! Hope to see you again soon.");
    }
}

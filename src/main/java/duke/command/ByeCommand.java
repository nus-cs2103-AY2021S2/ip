package duke.command;
import duke.exception.DukeException;
import duke.ui.Ui;
import duke.task.TaskList;

import java.util.LinkedList;
/**
 * It is a command object extends from Command for the Duke program.
 * When the parser calls it, it will receive the requests from the users
 * during the running of the program and change the status of the status
 * of command as wants to exit, and hence terminates the program.
 */
public class ByeCommand extends Command{
    /**
     * Constructor for ByeCommand object
     *
     * @param userMessage The message that the user inputs for further execution.
     */
    public ByeCommand(String userMessage){
        super(userMessage);
    }
    /**
     * The execution after parsing, it will change the static variable of all Command
     * object into exit = true, which means the user wants to exit.
     *
     * @param taskList The current taskList in the program.
     * @param ui The current ui in the program.
     */
    public void execute(TaskList taskList, Ui ui){
        exit = true;
        ui.display("Bye! Hope to see you again soon.");
    }
}

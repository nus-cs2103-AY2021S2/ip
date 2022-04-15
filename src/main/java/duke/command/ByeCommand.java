package duke.command;
import duke.task.TaskList;

/**
 * It is a command object extends from Command for the Duke program.
 * When the parser calls it, it receives the requests from the users
 * during the running of the program and change the status of the status
 * of command as wants to exit, and hence terminates the program.
 */
public class ByeCommand extends Command {

    /**
     * Constructor for ByeCommand object
     *
     * @param userMessage The message that the user inputs for further execution.
     */
    public ByeCommand(String userMessage) {
        super(userMessage);
    }

    /**
     * The execution after parsing, it changes the static variable of all Command
     * object into exit = true, which means the user wants to exit.
     *
     * @param taskList The current taskList in the program.
     * @return The Duke robot massage to the GUI.
     */
    public String execute(TaskList taskList) {
        isExit = true;
        return "Bye! Hope to see you again soon.";
    }
}

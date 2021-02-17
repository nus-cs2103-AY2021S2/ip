package duke.command;


import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

import java.util.LinkedList;

public class HelpCommand extends Command {
    /**
     * Constructor for HelpCommand object
     *
     * @param userMessage The message that the user inputs for further execution.
     */
    public HelpCommand(String userMessage) {
        super(userMessage);
    }

    /**
     * The execution after parsing, it will print out the command guide for the user.
     *
     * @param taskList The current taskList in the program. (This is to follow the execute method in Command Class, however in this method,
     *                  taskList is useless.
     *
     * @return The Duke robot massage to the GUI.
     */
    public String execute(TaskList taskList) throws DukeException {

        try {
            String start = "Here is the guide for you!";
            String addCommandHelp = "\n\n 1. To add a task: \n'todo {todoName}'\n\n 'event {eventName} /at {YYYY-MM-DD HH:MM}'\n\n "
                    + "'deadline {deadlineName} /by {YYYY-MM-DD HH:MM}'\n\n to ask Duke to add a task.\n";

            String priorityCommandHelp = "\n 2. To add a priority for a task: \nYou can add '-p {level of priority}' when adding a task.\n"
                    + "For example: 'todo go to school -p 4'\n The priority level range from 1 to 5, and should be an integer.";

            String botMessage = addCommandHelp + priorityCommandHelp;
            return botMessage;

        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! The description of a done is wrong.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The event index of a done is wrong.");
        }
    }
}

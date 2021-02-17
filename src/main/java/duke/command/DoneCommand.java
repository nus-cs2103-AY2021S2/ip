package duke.command;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

import java.util.LinkedList;
/**
 * It is a command object extends from Command for the Duke program.
 * When the parser calls it, it will receive the requests from the users
 * during the running of the program and starts to mark the users requested
 * task as done.
 */
public class DoneCommand extends Command {

    /**
     * Constructor for DoneCommand object
     *
     * @param userMessage The message that the user inputs for further execution.
     */
    public DoneCommand(String userMessage) {
        super(userMessage);
    }

    /**
     * The execution after parsing, it marks the task as done based on the index.
     * If the input is not correct, it raises an exception.
     *
     * @param taskList The current taskList in the program.
     * @return The Duke robot massage to the GUI.
     * @throws DukeException if there are some cases such as the index of the task is wrong or
     * wrong description of the command.
     */
    public String execute(TaskList taskList) throws DukeException {
        String [] arr = userMessage.split("\\s+");

        //Raises Exception if the input is like "done 1 2 3".
        if (arr.length > 2) throw new DukeException("OOPS!!! The description of a done is wrong.");

        try {
            // Possible exceptions like done A.
            int taskIndex = Integer.valueOf(arr[1]) - 1;
            LinkedList<Task> tasks = taskList.getTasks();
            Task task = tasks.get(taskIndex);

            task.markAsDone();

            StringBuilder builder = new StringBuilder();
            builder.append("Nice! I've marked this as done!\n");
            builder.append("[" + task.getStatusIcon() + "]" + task.toString() + " " + task.getPriorityIcon());
            String botMessage = builder.toString();
            return botMessage;

        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! The description of a done is wrong.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The event index of a done is wrong.");
        }
    }
}

package duke.command;
import duke.ui.Ui;
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
     * The execution after parsing, it will mark the task as done based on the index.
     * If the input is not correct, it will raise an exception.
     *
     * @param taskList The current taskList in the program.
     * @param ui The current ui in the program.
     * @throws DukeException if there are some cases such as the index of the task is wrong or
     * wrong description of the command.
     */
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        String [] arr = userMessage.split("\\s+");
        //Exception: If the input is like done 1 2 3:
        if (arr.length > 2) throw new DukeException("OOPS!!! The description of a done is wrong.");

        try {
            // Possible exceptions like done A.
            int taskIndex = Integer.valueOf(arr[1]) - 1;
            LinkedList<Task> tasks = taskList.getTasks();
            Task task = tasks.get(taskIndex);

            task.markAsDone();

            StringBuilder builder = new StringBuilder();
            builder.append("Nice! I've marked this as done!\n");
            builder.append("[" + task.getStatusIcon() + "]" + task.toString());
            String botMessage = builder.toString();
            ui.display(botMessage);

        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! The description of a done is wrong.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The event index of a done is wrong.");
        }
    }
}

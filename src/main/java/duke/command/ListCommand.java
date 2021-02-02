package duke.command;
import duke.exception.DukeException;
import duke.ui.Ui;
import duke.task.Task;
import duke.task.TaskList;

import java.util.LinkedList;
/**
 * It is a command object extends from Command for the Duke program.
 * When the parser calls it, it will receive the requests from the users
 * during the running of the program and starts to list all the tasks.
 */
public class ListCommand extends Command {

    /**
     * Constructor for ListCommand object
     *
     * @param userMessage The message that the user inputs for further execution.
     */
    public ListCommand(String userMessage) {
        super(userMessage);
    }

    /**
     * The execution after parsing, list out all the current tasks.
     *
     * @param taskList The current taskList in the program.
     * @param ui The current ui in the program.
     */
    public void execute(TaskList taskList, Ui ui) {
        int numOfTasks = taskList.getNumOfTasks();
        // Exception case
        if (numOfTasks == 0) {
            ui.display("No Tasks right now!");
        } else {
            LinkedList<Task> tasks = taskList.getTasks();
            StringBuilder builder = new StringBuilder();
            builder.append("Here are the tasks in your list\n");
            for (int i = 0; i < numOfTasks; i++) {
                Task task = tasks.get(i);
                String taskName = task.toString();
                String icon = task.getStatusIcon();
                String index = Integer.toString(i + 1);
                builder.append(index + ". " + "[" + icon + "]");
                builder.append(taskName + "\n");
            }
            String botMessage = builder.toString();
            ui.display(botMessage);
        }
        }
}


package duke.command;
import duke.task.Task;
import duke.task.TaskList;
import duke.exception.DukeException;
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
     * The execution after parsing, it lists out all the current tasks.
     *
     * @param taskList The current taskList in the program.
     * @return The Duke robot massage to the GUI.
     * @throws DukeException if there is no task in the list.
     */
    public String execute(TaskList taskList) throws DukeException {
        int numOfTasks = taskList.getNumOfTasks();
        if (numOfTasks == 0) {
            throw new DukeException("OOPS!!! No task right now!");
        }

        LinkedList<Task> tasks = taskList.getTasks();
        StringBuilder builder = new StringBuilder();
        builder.append("Here are the tasks in your list\n");
        for (int i = 0; i < numOfTasks; i++) {
            Task task = tasks.get(i);
            String taskName = task.toString();
            String icon = task.getStatusIcon();
            String index = Integer.toString(i + 1);
            String priorityIcon = task.getPriorityIcon();
            builder.append(index + ". " + "[" + icon + "]");
            builder.append(taskName + " " + priorityIcon + "\n");
        }

        String botMessage = builder.toString();
        return botMessage;

    }
}


package duke.command;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * It is a command object extends from Command for the Duke program.
 * When the parser calls it, it will receive the requests from the users
 * during the running of the program and starts add new todos to the task list.
 */
public class AddToDoCommand extends Command {
    /**
     * Constructor for AddToDoCommand object
     *
     * @param userMessage The message that the user inputs for further execution.
     */
    public AddToDoCommand(String userMessage) {
        super(userMessage);
    }

    /**
     * The execution after parsing, it will add a ToDo object into the tasks.
     * If the input is not correct, it will raise an exception.
     *
     * @param taskList The current taskList in the program.
     * @return The Duke robot massage to the GUI.
     * @throws DukeException if there are some cases such as no ToDo task name, then
     * it will raise the DukeException.
     */
    public String execute(TaskList taskList) throws DukeException {
        int spaceIndex = userMessage.indexOf(" ");
        boolean noName = spaceIndex == -1;
        boolean hasPriority = userMessage.substring(userMessage.length() - 4, userMessage.length() - 2).equals("-p");

        if (noName) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }

        StringBuilder builder = new StringBuilder();
        builder.append("Got it! I've added this task:\n");
        ToDo todo;
        if (!hasPriority) {
            String todoName = userMessage.substring(spaceIndex + 1);
            todo = new ToDo(todoName);
        } else {
            // input example: todo go to school -p 4
            String todoName = userMessage.substring(spaceIndex + 1, userMessage.length() - 5);
            int priority = Integer.parseInt(userMessage.substring(userMessage.length() - 1));
            todo = new ToDo(todoName, false, priority);
        }

        taskList.addTask(todo);
        builder.append("[" + todo.getStatusIcon() + "] " + todo.toString() + " " + todo.getPriorityIcon());
        builder.append("\nNow you have " + taskList.getNumOfTasks() + " tasks in the list.");

        String botMessage = builder.toString();
        return botMessage;
    }
}

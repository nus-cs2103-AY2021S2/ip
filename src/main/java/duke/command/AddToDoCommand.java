package duke.command;
import duke.ui.Ui;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.task.ToDo;

import java.util.LinkedList;
/**
 * It is a command object extends from Command for the Duke program.
 * When the parser calls it, it will receive the requests from the users
 * during the running of the program and starts add new todos to the task list.
 */
public class AddToDoCommand extends Command{
    /**
     * Constructor for AddToDoCommand object
     *
     * @param userMessage The message that the user inputs for further execution.
     */
    public AddToDoCommand(String userMessage){
        super(userMessage);
    }
    /**
     * The execution after parsing, it will add a ToDo object into the tasks.
     * If the input is not correct, it will raise an exception.
     *
     * @param taskList The current taskList in the program.
     * @param ui The current ui in the program.
     * @throws DukeException if there are some cases such as no ToDo task name, then
     * it will raise the DukeException.
     */
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        StringBuilder builder = new StringBuilder();
        builder.append("Got it! I've added this task:\n");
        int spaceIndex = userMessage.indexOf(" ");
        if (spaceIndex == -1) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
        String todoName = userMessage.substring(spaceIndex+1);
        ToDo todo = new ToDo(todoName);
        taskList.addTasks(todo);

        builder.append("[" + todo.getStatusIcon() + "] " + todo.toString());
        builder.append("\nNow you have " + Integer.toString(taskList.getNumOfTasks()) + " tasks in the list.");

        String botMessage = builder.toString();
        ui.display(botMessage);
    }
}

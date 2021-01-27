package duke.command;
import duke.ui.Ui;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.task.ToDo;



public class AddToDoCommand extends Command{

    public AddToDoCommand(String userMessage){
        super(userMessage);
    }

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

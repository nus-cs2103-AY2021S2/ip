package duke.command;
import duke.ui.Ui;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

import java.util.LinkedList;

public class DeleteCommand extends Command {

    public DeleteCommand(String userMessage) {
        super(userMessage);
    }

    public void execute(TaskList taskList, Ui ui) throws DukeException {
        String[] arr = userMessage.split("\\s+");
        //Exception: If the input is like delete 1 2 3:
        if (arr.length > 2) throw new DukeException("OOPS!!! The description of a delete is wrong.");
        try {
            // Possible exceptions like delete A.
            int taskIndex = Integer.valueOf(arr[1]) - 1;
            LinkedList<Task> tasks = taskList.getTasks();
            Task task = tasks.get(taskIndex);

            StringBuilder builder = new StringBuilder();
            builder.append("Noted. I've removed this task:\n");
            builder.append("[" + task.getStatusIcon() + "]" + task.toString());
            taskList.delete(task);
            builder.append("\nNow you have " + taskList.getNumOfTasks() + " tasks in the list.");
            String botMessage = builder.toString();
            ui.display(botMessage);
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! The description of a delete is wrong.");
        } catch (IndexOutOfBoundsException e){
            throw new DukeException("OOPS!!! The event index of a delete is wrong.");
        }
    }
}

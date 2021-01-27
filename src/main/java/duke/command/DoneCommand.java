package duke.command;
import duke.ui.Ui;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

import java.util.LinkedList;

public class DoneCommand extends Command{

    public DoneCommand(String userMessage){
        super(userMessage);
    }

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
            builder.append("["+task.getStatusIcon()+"]"+task.toString());
            String botMessage = builder.toString();
            ui.display(botMessage);

        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! The description of a done is wrong.");
        } catch (IndexOutOfBoundsException e){
            throw new DukeException("OOPS!!! The event index of a done is wrong.");
        }
    }
}

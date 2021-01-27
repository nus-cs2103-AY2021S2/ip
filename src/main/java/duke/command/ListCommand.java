package duke.command;
import duke.exception.DukeException;
import duke.ui.Ui;
import duke.task.Task;
import duke.task.TaskList;

import java.util.LinkedList;

public class ListCommand extends Command{

    public ListCommand(String userMessage){
        super(userMessage);
    }

    public void execute(TaskList taskList, Ui ui){
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


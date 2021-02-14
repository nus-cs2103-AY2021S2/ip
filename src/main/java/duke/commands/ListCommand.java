package duke.commands;

import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.ArrayList;

public class ListCommand extends Command {
    private TaskList taskList;

    public ListCommand(TaskList taskList) {
        super(taskList);
    }

    public TaskList execute() {
        return this.getTaskList();
    }

    public String toString() {
        String message = "Here are the tasks in your list:\n";
        ArrayList<Task> tasks = this.getTaskList().getList();
        int count = 1;
        for (Task t : tasks) {
            if (count == tasks.size()) {
                message += "     " + count + "." + t.toString();
            } else {
                message += "     " + count + "." + t.toString() + "\n";
            }
            count++;
        }
        return message;
    }
}

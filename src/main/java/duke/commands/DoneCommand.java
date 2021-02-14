package duke.commands;

import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.ArrayList;

public class DoneCommand extends Command{
    private TaskList taskList;
    private int indexOfTaskDone;

    public DoneCommand(TaskList taskList, int indexOfTaskDone) {
        super(taskList);
        this.indexOfTaskDone = indexOfTaskDone;
    }

    public TaskList execute() {
        ArrayList<Task> tasks = this.getTaskList().getList();
        for (int i = 0; i < tasks.size(); ++i) {
            if (i == this.indexOfTaskDone - 1) {
                tasks.set(i, tasks.get(i).markAsDone());
            }
        }
        return new TaskList(tasks);
    }

    public String toString() {
        TaskList tasks = this.getTaskList();
        String message = "Nice! I've marked this task as done:\n";
        for (int i = 0; i < tasks.size(); ++i) {
            if (i == indexOfTaskDone - 1) {
                message += tasks.get(i).markAsDone().toString();
            }
        }
        return message;
    }
}

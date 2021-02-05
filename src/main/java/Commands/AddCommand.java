package Commands;

import Tasks.Task;
import Tasks.TaskList;

public abstract class AddCommand extends Command {

    public abstract void execute(TaskList tasks);

    public boolean isExit() {
        return false;
    }

    protected void executeAddTask(TaskList tasks, Task newTask) {
        tasks.addTask(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask.getStatusString());
        System.out.println("Now you have " + tasks.getSize() + " task(s) in the list.");
    }
}
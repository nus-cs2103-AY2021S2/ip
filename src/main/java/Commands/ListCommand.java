package Commands;

import Tasks.TaskList;

public class ListCommand extends Command {
    public void execute(TaskList tasks) {
        if (tasks.getSize() == 0) {
            System.out.println("You have no tasks in your list yet :)");
        } else {
            System.out.println("Here are the task(s) in your list:");
            tasks.printTasks();
        }
    }

    public boolean isExit() {
        return false;
    }
}

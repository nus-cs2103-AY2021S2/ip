package Commands;

import Tasks.Task;
import Tasks.TaskList;

public class DeleteCommand extends Command {
    private final String description;

    public DeleteCommand(String description) {
        this.description = description;
    }

    public void execute(TaskList tasks) {
        Task deletedTask = tasks.popTaskByIndex(Integer.parseInt(description));
        if (null != deletedTask) {
            System.out.println("Noted. I've removed this task:");
            System.out.println(deletedTask.getStatusString());
        }
    }

    public boolean isExit() {
        return false;
    }
}

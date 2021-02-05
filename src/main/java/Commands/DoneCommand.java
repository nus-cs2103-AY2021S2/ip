package Commands;

import Tasks.Task;
import Tasks.TaskList;

public class DoneCommand extends Command {
    private final String description;

    public DoneCommand(String description) {
        this.description = description;
    }

    public void execute(TaskList tasks) {
        Task doneTask = tasks.getTaskByIndex(Integer.parseInt(description));
        if (null != doneTask) {
            doneTask.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(doneTask.getStatusString());
        }
    }

    public boolean isExit() {
        return false;
    }
}

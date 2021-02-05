package Commands;

import Tasks.Task;
import Tasks.TaskList;
import UserInterface.Ui;

public class DoneCommand extends Command {
    private final String description;

    public DoneCommand(String description) {
        this.description = description;
    }

    public void execute(TaskList tasks, Ui ui) {
        Task doneTask = tasks.getTaskByIndex(Integer.parseInt(description));
        if (null != doneTask) {
            doneTask.markAsDone();
            ui.handleDone(doneTask);
        }
    }

    public boolean isExit() {
        return false;
    }
}

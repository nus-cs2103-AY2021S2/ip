package duke.commands;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

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

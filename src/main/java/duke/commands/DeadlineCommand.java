package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.tasks.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

public class DeadlineCommand extends Command{
    private String deadlineDescription;
    private String period;

    public DeadlineCommand(String deadlineDescription, String period) {
        this.deadlineDescription = deadlineDescription;
        this.period = period;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task deadline = new Deadline(deadlineDescription, period);
            tasks.addTask(deadline);
            ui.showAddTask(deadline, tasks);
            storage.saveFile(tasks.getTaskList());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }

    public boolean isExit() {
        return false;
    }
}

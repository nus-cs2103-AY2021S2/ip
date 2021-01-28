package duke.command;

import duke.task.Task;
import duke.TaskList;
import duke.Ui;
import duke.Storage;

public class AddCommand extends Command{
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        ui.showAdded(task, tasks);
        storage.updateFile();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

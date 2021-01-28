package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.models.Task;

public abstract class AddCommand implements Command {
    private String taskName;
    public AddCommand(String taskName) {
        this.taskName = taskName;
    }

    public abstract Task getTask();

    public String getTaskName() {
        return taskName;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task curTask = getTask();
        tasks.addTask(curTask);
        ui.printTaskListStatus(tasks, curTask);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

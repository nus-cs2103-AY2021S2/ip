package command;

import component.Storage;
import component.TaskList;
import component.Ui;
import task.Task;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.getTasks().add(this.task);
        ui.showAdd(this.task, taskList);
    }
}

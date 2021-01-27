package duke.command;

import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;
import duke.task.Task;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (index > taskList.getTasks().size() - 1 || index < 0) {
            System.out.println("duke.task.Task does not exist");
            return;
        }
        Task t = taskList.getTasks().remove(index);
        ui.showDelete(t, taskList);
    }
}

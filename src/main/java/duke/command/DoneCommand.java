package command;

import component.Storage;
import component.TaskList;
import component.Ui;
import task.Task;

public class DoneCommand extends Command {
    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (index > taskList.getTasks().size() - 1 || index < 0) {
            System.out.println("Task does not exist!");
            return;
        }
        Task t = taskList.getTasks().get(index);
        t.markAsDone();
        ui.showDone(t);
    }
}

package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.io.IOException;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        Task deletedTask = taskList.getTask(index);
        taskList.deleteTask(index);
        storage.saveData(taskList);
        return ui.showTaskDeleted(deletedTask);
    }
}

package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.io.IOException;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        Task deletedTask = taskList.getTask(index);
        taskList.deleteTask(index);
        // System.out.printf(">>> Nice! I've marked this task as done:\n  [%s] [%s] %s\n",
        //         taskList..getTaskType(), newTask.getStatusIcon(), newTask.getTaskDescription());
        ui.printTaskDeleted(deletedTask);
        storage.saveData(taskList);
    }
}

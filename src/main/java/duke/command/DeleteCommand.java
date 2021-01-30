package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    @Override
    public void execute(String taskDescription, Task task, TaskList taskList, Storage storage) {
        // delete task
        taskList.removeTask(task);

        // update storage
        storage.saveTasksToStorage(taskList);

        // print message
        Ui.printTaskRemovedMessage(task);
    }
}

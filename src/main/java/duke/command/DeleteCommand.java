package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Command for delete input
 */
public class DeleteCommand extends Command {

    /**
     * Deletes the task from the task list, saves the task list in the internal storage and prints success message
     * @param task the task to be deleted
     * @param taskList the current instance of task list used by Duke
     * @param storage the storage instance used to save files into internal storage
     */
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

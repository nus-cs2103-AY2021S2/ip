package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Command for todo, event, deadline input
 */
public class AddCommand extends Command {

    /**
     * Adds the task into the task list, saves the task list in the internal storage and prints success message
     * @param task the task to be added into the task list
     * @param taskList the current instance of task list used by Duke
     * @param storage the storage instance used to save files into internal storage
     */
    @Override
    public String execute(String taskDescription, Task task, TaskList taskList, Storage storage) {
        // Add task to task list
        taskList.addTask(task);

        // Update Storage
        storage.saveTasksToStorage(taskList);

        // Print success message
        return Ui.taskAddedMessage(task);
    }

}

package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class AddCommand extends Command {
    @Override
    public void execute(Task task, TaskList taskList, Storage storage) {
        // Add task to task list
        taskList.addTask(task);

        // Update Storage
        storage.saveTasksToStorage(taskList);

        // Print success message
        Ui.printTaskAddedMessage(task);
    }

}

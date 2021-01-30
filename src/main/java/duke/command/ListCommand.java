package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Command for list input
 */
public class ListCommand extends Command {

    /**
     * Prints current list of tasks to user
     * @param task
     * @param taskList the current instance of task list used by Duke
     * @param storage
     */
    @Override
    public void execute(String taskDescription, Task task, TaskList taskList, Storage storage) {
        // print taskList
        Ui.printMessage("Here are the tasks in your list:\n" + taskList.toString());
    }
}

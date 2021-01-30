package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Command for done input
 */
public class DoneCommand extends Command {

    /**
     * Mark the task as done and prints success message
     * @param task the task to be set done
     * @param taskList
     * @param storage
     */
    @Override
    public void execute(String taskDescription, Task task, TaskList taskList, Storage storage) {
        // update task to done
        task.setDone();

        // print message
        Ui.printTaskDoneMessage(task);
    }
}

package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Command for invalid input
 */
public class InvalidCommand extends Command {

    /**
     * Prints invalid message
     * @param task
     * @param taskList
     * @param storage
     */
    @Override
    public String execute(String taskDescription, Task task, TaskList taskList, Storage storage) {
        // print invalid task message
        return Ui.invalidCommandMessage();
    }
}

package duke;

import java.io.IOException;

/**
 * Represents a delete command keyed in by the user.
 */
public class DeleteCommand extends Command {

    DeleteCommand(String[] parsedCommand) {
        super(parsedCommand);
    }

    /**
     * Executes the delete command by removing the task from the list, printing the deleted task to the user and
     * updating the stored file.
     *
     * @param taskManager TaskManager object that maintains the list of tasks.
     * @param ui Ui object that handles user interaction.
     * @param storage Storage object that handles the updating of stored file.
     * @throws IOException If the list of tasks are not in the correct storage format.
     * @throws DukeException If the task number specified as done is not valid.
     */
    public void execute(TaskManager taskManager, Ui ui, Storage storage) throws DukeException, IOException {
        String taskNumber = super.parsedCommand[1];
        Task completedTask = taskManager.delete(Integer.parseInt(taskNumber));
        ui.showRemovedTask(completedTask, taskManager.getNumOfTasks());
        storage.store(taskManager.retrieveTasksforStorage());
    }

}

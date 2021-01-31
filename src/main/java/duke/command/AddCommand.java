package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.TaskManager;
import duke.Ui;
import duke.task.Task;

/**
 * Represents an add command keyed in by the user.
 */
public class AddCommand extends Command {

    public AddCommand(String[] parsedCommand) {
        super(parsedCommand);
    }

    /**
     * Executes the add command by adding the task to the list, printing the added task to the user and updating
     * the stored file.
     *
     * @param taskManager TaskManager object that maintains the list of tasks.
     * @param ui Ui object that handles user interaction.
     * @param storage Storage object that handles the updating of stored file.
     * @return String string to output to user
     * @throws IOException If the list of tasks are not in the correct storage format.
     */
    public String execute(TaskManager taskManager, Ui ui, Storage storage) throws IOException {
        String taskType = super.parsedCommand[0];
        String description = super.parsedCommand[1];
        Task addedTask = taskManager.add(taskType, description, false);
        storage.store(taskManager.retrieveTasksforStorage());
        return ui.showAddedTask(addedTask, taskManager.getNumOfTasks());

    }
}

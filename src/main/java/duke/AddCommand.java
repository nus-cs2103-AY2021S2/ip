package duke;
import java.io.IOException;

/**
 * Represents an add command keyed in by the user.
 */
public class AddCommand extends Command {

    AddCommand(String[] parsedCommand) {
        super(parsedCommand);
    }

    /**
     * Executes the add command by adding the task to the list, printing the added task to the user and updating
     * the stored file.
     *
     * @param taskManager TaskManager object that maintains the list of tasks.
     * @param ui Ui object that handles user interaction.
     * @param storage Storage object that handles the updating of stored file.
     * @throws IOException If the list of tasks are not in the correct storage format.
     */
    public void execute(TaskManager taskManager, Ui ui, Storage storage) throws IOException {
        String taskType = super.parsedCommand[0];
        String description = super.parsedCommand[1];
        Task addedTask = taskManager.add(taskType, description, false);
        ui.showAddedTask(addedTask, taskManager.getNumOfTasks());
        storage.store(taskManager.retrieveTasksforStorage());
    }
}

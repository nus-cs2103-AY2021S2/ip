package duke;
import java.io.IOException;

/**
 * Represents a done command keyed in by the user.
 */
public class DoneCommand extends Command {

    DoneCommand(String[] parsedCommand) {
        super(parsedCommand);
    }

    /**
     * Executes the done command by marking the task as completed, printing the completed task to the user and
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
        Task doneTask = taskManager.done(Integer.parseInt(taskNumber));
        ui.showDoneTask(doneTask);
        storage.store(taskManager.retrieveTasksforStorage());
    }
}

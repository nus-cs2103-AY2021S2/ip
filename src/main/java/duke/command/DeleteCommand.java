package duke.command;

import java.io.IOException;
import java.util.ArrayList;

import duke.DukeException;
import duke.Storage;
import duke.TaskManager;
import duke.Ui;
import duke.task.Task;

/**
 * Represents a delete command keyed in by the user.
 */
public class DeleteCommand extends Command {

    public DeleteCommand(String[] parsedCommand) {
        super(parsedCommand);
    }

    /**
     * Executes the delete command by removing the task from the list, printing the deleted task to the user and
     * updating the stored file.
     *
     * @param taskManager TaskManager object that maintains the list of tasks.
     * @param ui Ui object that handles user interaction.
     * @param storage Storage object that handles the updating of stored file.
     * @return String string to output to user
     * @throws IOException If the list of tasks are not in the correct storage format.
     * @throws DukeException If the task number specified as done is not valid.
     */
    public String execute(TaskManager taskManager, Ui ui, Storage storage) throws DukeException, IOException {
        String[] taskNumbers = super.parsedCommand[1].split(" ");
        ArrayList<Task> deletedTasks = new ArrayList<>();
        for (int i = 0; i < taskNumbers.length; i++) {
            Task task = taskManager.delete(Integer.parseInt(taskNumbers[i]) - i);
            deletedTasks.add(task);
        }
        storage.store(taskManager.retrieveTasksforStorage());
        return ui.showRemovedTask(deletedTasks, taskManager.getNumOfTasks());
    }

}

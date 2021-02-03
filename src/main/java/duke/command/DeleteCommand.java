package duke.command;

import duke.util.TaskList;
import duke.util.TaskStorage;
import duke.util.Ui;
import duke.exception.DukeStorageException;
import duke.task.Task;

/**
 * Class representing a Delete Command.
 */
public class DeleteCommand extends Command {
    private int indexToDelete;

    /**
     * Constructor of DeleteCommand.
     *
     * @param indexToDelete The index of the task to be deleted.
     */
    public DeleteCommand(int indexToDelete) {
        this.indexToDelete = indexToDelete;
    }

    /**
     * Executes the delete command.
     * Removes task from list of tasks.
     * Updates the task storage.
     * Prompts the user that the task has been removed and show the total number of tasks.
     *
     * @param tasks List of tasks.
     * @param ui Formats and prints message to user.
     * @param storage Updates tasks.txt of the removed task.
     * @return true.
     */
    public String execute(TaskList tasks, Ui ui, TaskStorage storage) {
        try {
            Task taskRemoved = tasks.deleteTask(indexToDelete);
            storage.storeData(tasks);
            return ui.formatRemoveCmdMsg(taskRemoved, tasks);
        } catch (DukeStorageException e) {
            return e.getMessage();
        }
    }
}

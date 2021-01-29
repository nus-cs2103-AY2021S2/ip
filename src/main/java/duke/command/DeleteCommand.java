package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * DeleteCommand represent a command to delete a task from the list.
 */
public class DeleteCommand extends Command{

    /**
     * Returns a DeleteCommand that will delete a specific task.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(String index) {
        super(null, index, null,null, false);
    }

    /**
     * Delete the task with the given index from the list. Then, outputs a message
     * when the task is successfully deleted. Save the latest list to the data file.
     *
     * @param tasks TaskList storing the current tasks.
     * @param ui The user interface of the program.
     * @param storage The storage of the program.
     * @throws DukeException If an I/O error occurs or the index if out of bounds of the list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = tasks.delete(description);
        ui.showDelete(t.toString(), tasks.getSize());
        storage.save(tasks.listOutTaskInString());
    }
}

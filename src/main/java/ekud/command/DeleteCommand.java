package ekud.command;

import ekud.common.exception.DukeException;
import ekud.storage.Storage;
import ekud.task.Task;
import ekud.task.TaskList;
import ekud.ui.Ui;

/**
 * Command that deletes a task on the list.
 */
public class DeleteCommand extends ModificationCommand {
    protected int index;

    /**
     * Construct a command that deletes a particular task base on the index.
     *
     * @param index The index of the task as given by the "list" command.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Delete a task by its index.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The file writer.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task deletedTask = tasks.remove(index);
        super.execute(tasks, ui, storage);

        ui.printLines(
                "Poof! This task is gone:",
                "\t" + deletedTask.toString(),
                getTasksLeftString(tasks));
    }
}

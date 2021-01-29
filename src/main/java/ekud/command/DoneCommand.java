package ekud.command;

import ekud.common.exception.EkudException;
import ekud.storage.Storage;
import ekud.task.Task;
import ekud.task.TaskList;
import ekud.ui.Ui;

/**
 * Command that marks a task as completed.
 */
public class DoneCommand extends ModificationCommand {
    protected int index;

    /**
     * Construct a command that marks a task as completed.
     *
     * @param index The index of the task as given by the "list" command.
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Mark the task at index as done.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The file writer.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EkudException {
        Task doneTask = tasks.markDone(index);
        super.execute(tasks, ui, storage);

        ui.printLines(
                "Good job! The task below is marked done!",
                "\t" + doneTask.toString());
    }
}

package ekud.command;

import ekud.common.exception.EkudException;
import ekud.storage.Storage;
import ekud.task.Task;
import ekud.task.TaskList;

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
     * @param storage The file writer.
     * @return Summary of the task marked done.
     * @throws EkudException If task saving fails.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws EkudException {
        Task doneTask = tasks.markDone(index);

        return String.join(System.lineSeparator(),
                super.execute(tasks, storage),
                "Good job! The task below is marked done!",
                "  " + doneTask.toString());
    }
}

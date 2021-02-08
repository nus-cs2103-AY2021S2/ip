package ekud.command;

import ekud.common.exception.EkudException;
import ekud.storage.Storage;
import ekud.task.Task;
import ekud.task.TaskList;

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
     * @param storage The file writer.
     * @return Summary of the task deleted.
     * @throws EkudException If task saving fails.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws EkudException {
        Task deletedTask = tasks.remove(index);
        return String.join(System.lineSeparator(),
                super.execute(tasks, storage),
                "Poof! This task is gone:",
                "\t" + deletedTask.toString(),
                getTasksLeftString(tasks));
    }
}

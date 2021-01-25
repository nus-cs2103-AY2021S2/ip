package ekud.command;

import ekud.common.exception.DukeException;
import ekud.storage.Storage;
import ekud.task.TaskList;
import ekud.ui.Ui;

/**
 * Abstract base class for all tasks that causes changes to the list of tasks
 */
public abstract class ModificationCommand extends Command {
    /**
     * Generate summary of how many tasks are left.
     *
     * @param tasks The list of tasks.
     * @return String containing task summary.
     */
    protected static String getTasksLeftString(TaskList tasks) {
        return String.format("Err you got %d task%s in total leh", tasks.size(), tasks.size() <= 1 ? "" : "s");
    }

    /**
     * Save all tasks to disk.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The file writer.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.save(tasks);
    }
}

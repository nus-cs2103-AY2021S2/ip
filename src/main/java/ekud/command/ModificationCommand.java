package ekud.command;

import ekud.common.exception.EkudException;
import ekud.storage.Storage;
import ekud.task.TaskList;

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
     * @param storage The file writer.
     * @return Empty string;
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws EkudException {
        storage.save(tasks);
        return "";
    }
}

package ekud.command;

import ekud.common.exception.EkudException;
import ekud.storage.Storage;
import ekud.task.TaskList;

/**
 * Abstract base class common to all task adding commands.
 */
public abstract class AddCommand extends ModificationCommand {
    protected String description;

    /**
     * Construct a task-adding command.
     *
     * @param description The description of the new task to be created
     */
    public AddCommand(String description) {
        this.description = description;
    }

    /**
     * Save all tasks to disk.
     *
     * @param tasks   The list of tasks.
     * @param storage The file writer.
     * @return Summary of the task added.
     * @throws EkudException If task saving fails.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws EkudException {
        return String.join(System.lineSeparator(),
                super.execute(tasks, storage),
                "Okay I remember for you liao:",
                "  " + tasks.last(),
                getTasksLeftString(tasks));
    }
}

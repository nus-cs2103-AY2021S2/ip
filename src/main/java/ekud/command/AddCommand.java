package ekud.command;

import ekud.common.exception.EkudException;
import ekud.storage.Storage;
import ekud.task.TaskList;
import ekud.ui.Ui;

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
     * @param ui      The user interface.
     * @param storage The file writer.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EkudException {
        super.execute(tasks, ui, storage);
        ui.printLines("Okay I remember for you liao:",
                "\t" + tasks.last(),
                getTasksLeftString(tasks));
    }
}

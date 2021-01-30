package ekud.command;

import ekud.common.exception.EkudException;
import ekud.storage.Storage;
import ekud.task.TaskList;

/**
 * Command that exits the application.
 */
public class ByeCommand extends Command {
    /**
     * Save the current list of tasks to disk.
     *
     * @param tasks   The list of tasks.
     * @param storage The file writer.
     */
    @Override
    public String execute(final TaskList tasks, Storage storage) throws EkudException {
        storage.save(tasks); // probably can skip since tasks are written to disk on every modification
        return "Bye bye. Anything call me ah!";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

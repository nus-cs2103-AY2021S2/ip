package ekud.command;

import ekud.common.exception.EkudException;
import ekud.storage.Storage;
import ekud.task.TaskList;

/**
 * Abstract base class for all commands
 */
public abstract class Command {
    /**
     * Execute the command and perform the necessary manipulations.
     *
     * @param tasks   The list of tasks.
     * @param storage The file writer.
     * @return The result of the execution.
     * @throws EkudException If errors are encountered during execution.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws EkudException;

    /**
     * Return whether the current command is a signal to exit the application. Only the appropriate tasks should
     * override this method.
     *
     * @return True if the task triggers the application to exit, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}

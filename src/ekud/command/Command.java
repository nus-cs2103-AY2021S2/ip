package ekud.command;

import ekud.common.exception.DukeException;
import ekud.storage.Storage;
import ekud.task.TaskList;
import ekud.ui.Ui;

/**
 * Abstract base class for all commands
 */
public abstract class Command {
    /**
     * Execute the command and perform the necessary manipulations.
     *
     * @param tasks the list of tasks
     * @param ui the user interface
     * @param storage the file writer
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Return whether the current command is a signal to exit the application.
     * Only the appropriate tasks should override this method
     *
     * @return true if the task triggers the application to exit, false otherwise
     */
    public boolean isExit() {
        return false;
    }
}

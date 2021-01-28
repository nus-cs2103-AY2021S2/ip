package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.exception.DukeException;

/**
 * An abstract class that represents a Command.
 */
public abstract class Command {
    /**
     * Performs the behavior of this specific command.
     * @param tasks Task list given.
     * @param ui User interface class object.
     * @param storage Storage path that is going to be updated.
     * @throws DukeException If error occurs during the process.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns false if this command is not ExitCommand.
     * @return False if this command is not ExitCommand and true otherwise.
     */
    public abstract boolean isExit();
}

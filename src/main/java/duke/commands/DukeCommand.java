package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.FileLoader;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Superclass for all commands.
 */
public abstract class DukeCommand {

    /**
     * Passes instruction to main loop to terminate program.
     *
     * False by default for most commands.
     *
     * @return false
     */
    public boolean isExit() {
        return false;
    }

    public abstract void execute(TaskList tasks, Ui ui, FileLoader loader) throws DukeException;
}

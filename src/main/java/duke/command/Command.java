package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * An abstract Command class.
 */
public abstract class Command {
    /**
     * Constructs Command.
     */
    public Command() {
    }

    /**
     * Abstract execute method for Command.
     * @param taskList The list of tasks.
     * @param ui The user interface.
     * @param storage The storage handler.
     * @throws DukeException If there is error in execution.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    /**
     * Abstract isExit method to determine if execution will exit program.
     * @return True if program exits, False if program continues.
     */
    public abstract boolean isExit();
}

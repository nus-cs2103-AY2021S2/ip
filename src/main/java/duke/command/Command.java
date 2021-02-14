package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

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
     *
     * @param tasks The list of tasks.
     * @param storage  The storage handler.
     * @return Output for GUI.
     * @throws DukeException If there is error in execution.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws DukeException;
}

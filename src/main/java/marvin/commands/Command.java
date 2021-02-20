package marvin.commands;

import marvin.exception.DukeException;
import marvin.storage.Storage;
import marvin.task.TaskList;

/**
 * Represents a user command which can be executed.
 */
public abstract class Command {
    protected boolean isExit;

    /**
     * Constructor.
     * Default exit status of program is false.
     */
    public Command() {
        this.isExit = false;
    }

    /**
     * Returns the exit status of the program.
     * @return Exit status of program.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Executes the user command and returns a response message
     * @param tasks The state of the current task list.
     * @param storage The storage used by the program.
     * @return Response message.
     * @throws DukeException
     */
    public abstract String execute(TaskList tasks, Storage storage) throws DukeException;
}

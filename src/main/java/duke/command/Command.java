package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Abstract class for all various commands.
 */
public abstract class Command {

    /**
     * Executes the Command in DataHandler.
     *
     * @param tasks list of tasks where this new task is added to
     * @param input details of the task
     * @param storage handles the various tasks according to their type
     */
    public abstract void execute(TaskList tasks, String input, Storage storage) throws IOException;

    /**
     * Checks if it is time to exit Duke.
     */
    public abstract boolean isExit();
}

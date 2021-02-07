package monica.command;

import monica.MonicaException;
import monica.Storage;
import monica.task.TaskList;
import monica.ui.Ui;

/**
 * Represents a general command which can be executed and can decide whether to exit the programme or stay.
 */
public abstract class Command {

    /**
     * Executes the command.
     * @param tasks The task list used for execution of the command.
     * @param ui Interactions with users.
     * @param storage Data stored in the local file path.
     * @throws MonicaException If there is any invalid command.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws MonicaException;

    /**
     * Returns a boolean value to signal the chat bot to exit.
     * @return False if the command does not signal the bot to exit.
     */
    public abstract boolean isExit();
}

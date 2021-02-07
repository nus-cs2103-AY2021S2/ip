package monica.command;

import monica.Storage;
import monica.task.TaskList;
import monica.ui.Ui;

/**
 * Represents a command that makes the chat bot exit.
 */
public class ExitCommand extends Command {
    /**
     * Executes the command.
     * @param tasks The task list used for execution of the command.
     * @param ui Interactions with users.
     * @param storage Data stored in the local file path.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.sayBye();
    }

    /**
     * Returns a boolean value to signal the chat bot to exit.
     * @return True as the command signals the bot to exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}

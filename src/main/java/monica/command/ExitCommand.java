package monica.command;

import monica.Storage;
import monica.task.TaskList;
import monica.ui.Ui;

/**
 * Represents a command that makes the chat bot exit.
 */
public class ExitCommand extends Command {
    /**
     * Executes bye command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showFarewell();
    }
}

package monica.command;

import monica.MonicaException;
import monica.Storage;
import monica.task.TaskList;
import monica.ui.Ui;

/**
 * Represents a command that provides user with guidance on commands.
 */
public class HelpCommand extends Command {

    /**
     * Executes help command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MonicaException {
        return ui.showHelp();
    }
}

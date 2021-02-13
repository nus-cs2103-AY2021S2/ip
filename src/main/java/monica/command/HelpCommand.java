package monica.command;

import monica.MonicaException;
import monica.Storage;
import monica.task.TaskList;
import monica.ui.Ui;

public class HelpCommand extends Command{
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MonicaException {
        return ui.showHelp();
    }
}

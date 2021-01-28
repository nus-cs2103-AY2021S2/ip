package Commands;

import Storage.Storage;
import TaskList.TaskList;
import Ui.Ui;

public class HelpCommand extends Command {

    @Override
    public void execute(TaskList t, Ui ui, Storage storage) {
        ui.help();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

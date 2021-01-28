package Commands;

import Exceptions.DukeException;
import Storage.Storage;
import TaskList.TaskList;
import Ui.Ui;

public class ByeCommand extends Command {

    @Override
    public void execute(TaskList t, Ui ui, Storage storage) {
        ui.goodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

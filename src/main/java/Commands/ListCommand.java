package Commands;

import Storage.Storage;
import TaskList.TaskList;
import Ui.Ui;

public class ListCommand extends Command{

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        ui.list(tasklist);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

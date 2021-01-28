package Commands;

import Storage.Storage;
import TaskList.TaskList;
import Ui.Ui;

public class ListCommand extends Command{

    /**
     * Executes the List Command by presenting the displaying the DukeTasks in the TaskList.
     *
     * @param tasklist Duke TaskList object.
     * @param storage Duke Storage object.
     * @param ui Duke UI object.
     */
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        ui.list(tasklist);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

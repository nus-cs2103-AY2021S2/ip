package commands;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class ListCommand extends Command {

    /**
     * Executes the List Command by presenting the displaying the DukeTasks in the TaskList.
     *
     * @param tasklist Duke TaskList object.
     * @param storage Duke Storage object.
     * @param ui Duke UI object.
     */
    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage) {
        return ui.list(tasklist);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

package commands;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class ByeCommand extends Command {

    /**
     * Executes the Bye Command by presenting the GoodBye Message.
     *
     * @param tasklist Duke TaskList object.
     * @param storage Duke Storage object.
     * @param ui Duke UI object.
     */
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        ui.goodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

package Commands;

import Exceptions.DukeException;
import Storage.Storage;
import TaskList.TaskList;
import Ui.Ui;

public class ByeCommand extends Command {

    /**
     * Executes the Bye Command by presenting the GoodBye Message.
     *
     * @param tasklist Duke TaskList object.
     * @param storage Duke Storage object.
     * @param ui Duke UI object.
     */
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws DukeException {
        ui.goodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

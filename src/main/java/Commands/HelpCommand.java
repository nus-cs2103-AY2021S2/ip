package Commands;

import Exceptions.DukeException;
import Storage.Storage;
import TaskList.TaskList;
import Ui.Ui;

public class HelpCommand extends Command {

    /**
     * Executes the Help Command by presenting the Help Message for the different commands.
     *
     * @param tasklist Duke TaskList object.
     * @param storage Duke Storage object.
     * @param ui Duke UI object.
     */
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        ui.help();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

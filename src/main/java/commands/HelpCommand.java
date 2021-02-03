package commands;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class HelpCommand extends Command {

    /**
     * Executes the Help Command by presenting the Help Message for the different commands.
     *
     * @param tasklist Duke TaskList object.
     * @param storage Duke Storage object.
     * @param ui Duke UI object.
     */
    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage) {
        return ui.help();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

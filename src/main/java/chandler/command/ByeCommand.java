package chandler.command;

import chandler.ChandlerException;
import chandler.Main;
import chandler.Storage;
import chandler.TaskList;
import chandler.ui.Ui;

public class ByeCommand implements Command {
    private Ui ui;

    /**
     * Create and initialize a Bye Command.
     *
     * @param fullCmd The full user input in String form.
     * @param ui The ui object responsible for displaying bye messages to the CLI.
     */
    public ByeCommand(String fullCmd, Ui ui) {
        this.ui = ui;
    }

    @Override
    public String run(Storage storage, TaskList taskList) throws ChandlerException {
        Main.exit();
        return ui.returnBye();
    }
}

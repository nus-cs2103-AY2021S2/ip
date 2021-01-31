package duke.command;

import duke.Storage;
import duke.TaskManager;
import duke.Ui;

public class ExitCommand extends Command {

    /**
     *  ExitCommand constructor.
     */
    public ExitCommand() {
        //do nothing
    }

    /**
     *  Executes ExitCommand.
     *
     *  @param ui Ui Object from Duke.
     *  @param tm TaskManager Object from Duke.
     *  @param st Storage Object from Duke.
     */
    public String execute(Ui ui, TaskManager tm, Storage st) {
        ui.showByeBye();
        return "Thanks for using me!";
    }

    @Override
    public boolean isExit() {
        return true;
    }

}

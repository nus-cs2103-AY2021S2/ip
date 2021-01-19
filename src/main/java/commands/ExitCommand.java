package commands;

import duke.TaskManager;
import duke.Ui;

public class ExitCommand extends Command {

    public ExitCommand() {
        //do nothing
    }

    public void execute(Ui ui, TaskManager tm) {
        ui.showByeBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }

}

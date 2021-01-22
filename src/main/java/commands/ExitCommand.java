package commands;

import duke.Storage;
import duke.TaskManager;
import duke.Ui;

public class ExitCommand extends Command {

    public ExitCommand() {
        //do nothing
    }

    public void execute(Ui ui, TaskManager tm, Storage st) {
        ui.showByeBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }

}

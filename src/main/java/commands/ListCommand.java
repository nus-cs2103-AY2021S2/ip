package commands;

import duke.Ui;
import duke.TaskManager;

public class ListCommand extends Command {
    public ListCommand() {
        //do nothing
    }

    public void execute(Ui ui, TaskManager tm) {
        ui.showTasks(tm.getTasks());
    }
}

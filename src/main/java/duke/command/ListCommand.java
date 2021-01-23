package duke.command;

import duke.Storage;
import duke.TaskManager;
import duke.Ui;

public class ListCommand extends Command {
    public ListCommand() {
        //do nothing
    }

    public void execute(Ui ui, TaskManager tm, Storage st) {
        ui.showTasks(tm.getTasks());
    }
}

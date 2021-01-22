package duke.command;

import duke.Storage;
import duke.Ui;
import duke.TaskManager;

public class ListCommand extends Command {
    public ListCommand() {
        //do nothing
    }

    public void execute(Ui ui, TaskManager tm, Storage st) {
        ui.showTasks(tm.getTasks());
    }
}

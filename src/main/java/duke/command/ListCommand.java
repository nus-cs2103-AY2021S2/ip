package duke.command;

import duke.Storage;
import duke.TaskManager;
import duke.Ui;

public class ListCommand extends Command {

    /**
     *  ListCommand constructor.
     */
    public ListCommand() {
        //do nothing
    }

    /**
     *  Executes ListCommand.
     *
     *  @param ui Ui Object from Duke.
     *  @param tm TaskManager Object from Duke.
     *  @param st Storage Object from Duke.
     */
    public void execute(Ui ui, TaskManager tm, Storage st) {
        ui.showTasks(tm.getTasks());
    }
}

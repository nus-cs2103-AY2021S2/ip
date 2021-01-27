package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {

    @Override
    public boolean execute(Ui ui, TaskList tasks, Storage storage) {
        ui.showUserAllTasks(tasks);
        return EXECUTION_SUCCESS;
    }
}

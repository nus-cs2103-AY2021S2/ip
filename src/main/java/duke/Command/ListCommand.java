package duke.Command;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

public class ListCommand extends Command {

    @Override
    public boolean execute(Ui ui, TaskList tasks, Storage storage) {
        ui.showUserAllTasks(tasks);
        return EXECUTION_SUCCESS;
    }
}

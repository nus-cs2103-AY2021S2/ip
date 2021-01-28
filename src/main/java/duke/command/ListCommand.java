package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

public class ListCommand extends Command {
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printTasks(taskList);
    }
}

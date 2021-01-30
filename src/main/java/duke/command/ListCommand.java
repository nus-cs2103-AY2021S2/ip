package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(Task task, TaskList taskList, Storage storage) {
        // print taskList
        Ui.printMessage(taskList.toString());
    }
}

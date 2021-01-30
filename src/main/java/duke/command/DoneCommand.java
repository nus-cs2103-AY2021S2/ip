package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class DoneCommand extends Command {

    @Override
    public void execute(String taskDescription, Task task, TaskList taskList, Storage storage) {
        // update task to done
        task.setDone();

        // print message
        Ui.printTaskDoneMessage(task);
    }
}

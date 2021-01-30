package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;

public abstract class Command {

    public abstract void execute(String taskDescription, Task task, TaskList taskList, Storage storage);

    public boolean isDukeOnline() {
        return true;
    }
}

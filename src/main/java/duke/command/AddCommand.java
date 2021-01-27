package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.storage.Storage;
import duke.exception.DukeException;

public abstract class AddCommand extends Command {
    public void addThisTask(TaskList tasks, Task task, Storage storage) {
        System.out.println(" Added: ");
        tasks.add(task);
        storage.updateInFile(tasks);
        System.out.println("  " + task);
        System.out.println(" Now you have " + tasks.size() + " tasks.");
    }

    public abstract Task getTask() throws DukeException;

    @Override
    public boolean isExit() {
        return false;
    }
}

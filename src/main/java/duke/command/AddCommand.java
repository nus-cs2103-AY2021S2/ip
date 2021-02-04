package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * An abstract class that represents "adding task" command.
 */
public abstract class AddCommand extends Command {
    /**
     * Adds the task into a given task list while updating the file.
     * @param tasks Task list.
     * @param task Task to be added.
     * @param storage Storage path that is going to be updated.
     */
    public void addThisTask(TaskList tasks, Task task, Ui ui, Storage storage) {
        tasks.add(task);
        storage.updateInFile(tasks);
        ui.printTaskAdded(task, tasks);
    }

    /**
     * Returns false if this command is not ExitCommand.
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

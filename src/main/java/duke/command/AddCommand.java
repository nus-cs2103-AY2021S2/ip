package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.storage.Storage;
import duke.exception.DukeException;

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
    public void addThisTask(TaskList tasks, Task task, Storage storage) {
        System.out.println(" Added: ");
        tasks.add(task);
        storage.updateInFile(tasks);
        System.out.println("  " + task);
        System.out.println(" Now you have " + tasks.size() + " tasks.");
    }

    protected abstract Task getTask() throws DukeException;

    /**
     * Returns false if this command is not ExitCommand.
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

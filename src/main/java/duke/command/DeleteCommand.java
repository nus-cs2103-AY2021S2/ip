package duke.command;

import duke.exception.TaskIndexOutOfBoundsException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A class represents a DeleteCommand.
 */
public class DeleteCommand extends IndexCommand {
    /**
     * Constructs a DeleteCommand.
     * @param index The index of the task that is going to be deleted.
     */
    public DeleteCommand(int index) {
        super(index);
    }

    /**
     * Deletes the specified task from the task list.
     * @param tasks Task list given.
     * @param ui User interface class object.
     * @param storage Storage path that is going to be updated.
     * @return The message produced by deleting a task.
     * @throws TaskIndexOutOfBoundsException If the specified index is out of the bound of the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws TaskIndexOutOfBoundsException {
        if (index < tasks.size() && index >= 0) {
            Task removingTask = tasks.get(index);
            tasks.remove(index);
            storage.updateInFile(tasks);
            ui.printTaskRemoved(removingTask, tasks);
            return ui.deleteTaskResponse(removingTask, tasks);
        } else {
            throw new TaskIndexOutOfBoundsException("There is no task numbered " + (index + 1) + "!");
        }

    }
}

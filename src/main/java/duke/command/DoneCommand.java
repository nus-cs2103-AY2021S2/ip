package duke.command;

import duke.exception.TaskIndexOutOfBoundsException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A class represents a DoneCommand.
 */
public class DoneCommand extends IndexCommand {
    /**
     * Constructs a DoneCommand.
     * @param index The index of the task that is completed.
     */
    public DoneCommand(int index) {
        super(index);
    }

    /**
     * Marks the specified task in the task list as completed.
     * @param tasks Task list given.
     * @param ui User interface class object.
     * @param storage Storage path that is going to be updated.
     * @return The message produced by completing a task.
     * @throws TaskIndexOutOfBoundsException If the specified index is out of the bound of task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws TaskIndexOutOfBoundsException {
        if (index < tasks.size() && index >= 0) {
            Task completedTask = tasks.get(index);
            completedTask.complete();
            storage.updateInFile(tasks);
            ui.printTaskCompleted(completedTask);
            return ui.completeTaskResponse(completedTask);
        } else {
            throw new TaskIndexOutOfBoundsException("There is no task numbered " + (index + 1) + "!");
        }
    }
}

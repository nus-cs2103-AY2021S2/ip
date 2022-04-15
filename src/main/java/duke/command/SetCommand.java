package duke.command;

import duke.exception.TaskIndexOutOfBoundsException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class SetCommand extends IndexCommand {
    private final int priority;

    /**
     * Constructs a SetCommand with given index and priority.
     * @param index The index of the task to be updated.
     * @param priority The priority to be assigned to the task.
     */
    public SetCommand(int index, int priority) {
        super(index);
        this.priority = priority;
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
            Task task = tasks.get(index);
            task.setPriority(priority);
            storage.updateInFile(tasks);
            ui.printTaskUpdated(task);
            return ui.updateTaskResponse(task);
        } else {
            throw new TaskIndexOutOfBoundsException("There is no task numbered " + (index + 1) + "!");
        }
    }
}

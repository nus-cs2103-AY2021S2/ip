package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.models.Task;
import duke.ui.Ui;

/**
 * Abstract class for commands adding new tasks to the list.
 */
public abstract class AddCommand implements Command {
    private String taskName;
    public AddCommand(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Gets the task to be added to the list.
     * @return task to be added to the list
     */
    public abstract Task getTask();

    /**
     * Returns the name of the task.
     * @return name of the task
     */
    public String getTaskName() {
        return taskName;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task curTask = getTask();
        tasks.addTask(curTask);
        ui.printTaskListStatus(tasks, curTask);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

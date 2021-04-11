package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.models.Task;
import duke.ui.Ui;

/**
 * Abstract class for commands adding new tasks to the list.
 */
public abstract class AddCommand implements Command {
    private final String taskName;

    protected AddCommand(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Gets the task to be added to the list.
     *
     * @return task to be added to the list
     */
    public abstract Task getTask();

    /**
     * Returns whether the given task can be added into the tasklist.
     *
     * @return whether task can added into the tasklist
     */
    public abstract boolean isTaskValid(Task task, TaskList tasks) throws DukeException;

    /**
     * Returns the name of the task.
     *
     * @return name of the task
     */
    public String getTaskName() {
        return taskName;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task curTask = getTask();
        assert (curTask != null);
        if (!isTaskValid(curTask, tasks)) {
            ui.printError("Unable to add invalid task");
            return;
        }
        tasks.addTask(curTask);
        ui.printTaskListStatus(tasks, curTask);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

package duke.commands;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Handles the logic of marking a task in the to-do as done.
 */
public class DoneCommand extends Command {
    private final int index;
    private Task doneTask;

    /**
     * Initializes a command to mark a <code>Task</code> in the to-do list as done.
     * The task will be identified using its index in the application's <code>TaskList</code>.
     *
     * @param index Index of the task to be marked as done.
     */
    public DoneCommand(int index) {
        this.index = index;
        this.doneTask = null;
    }

    /**
     * Returns false as marking tasks as done should not terminate the application.
     *
     * @return false
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Mark a <code>Task</code> in the input <code>TaskList</code>, identified by its index, as done.
     *
     * @param tasks A collection of <code>Task</code> objects representing the application's state.
     */
    public void execute(TaskList tasks) {
        this.doneTask = tasks.getTaskByIndex(this.index);
        if (null != this.doneTask) {
            this.doneTask.markAsDone();
        }
    }

    /**
     * Computes a response to notify the users if a <code>Task</code> is marked as done.
     *
     * @param tasks A collection of <code>Task</code> objects representing the application's state.
     * @param ui    A handler to manage the application's user-interface layer.
     * @return A <code>String</code> to respond to marking a <code>Task</code> as done.
     */
    public String getResponse(TaskList tasks, Ui ui) {
        return ui.handleDone(this.doneTask, this.index);
    }
}

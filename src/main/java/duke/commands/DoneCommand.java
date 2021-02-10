package duke.commands;

import duke.exceptions.TaskNumberNotExistException;
import duke.tasks.Task;
import duke.tasks.TaskList;

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
        // Set this.doneTask to the task to be marked as done (if any), or else null.
        this.doneTask = tasks.getTaskByIndex(this.index);
        if (null != this.doneTask) {
            this.doneTask.markAsDone();
        }
    }

    /**
     * Computes a response to notify the users if a <code>Task</code> is marked as done.
     *
     * @param tasks A collection of <code>Task</code> objects representing the application's state.
     * @return A <code>String</code> to respond to marking a <code>Task</code> as done.
     */
    public String getResponse(TaskList tasks) {
        try {
            // Note that this getResponse method MUST be called after the execute method. Following
            // this condition, iff this.doneTask is equal to null here, then no task was marked as
            // done earlier. That can only mean that the index input into this command does not exist.
            if (null == this.doneTask) {
                throw new TaskNumberNotExistException(this.index);
            }
        } catch (TaskNumberNotExistException e) {
            return e.getMessage();
        }

        return "Nice! I've marked this task as done:\n" + this.doneTask.getStatusString();
    }
}

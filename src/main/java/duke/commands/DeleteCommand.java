package duke.commands;

import duke.exceptions.TaskNumberNotExistException;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Handles the logic of deleting a task from the to-do list.
 */
public class DeleteCommand extends Command {
    private final int index;
    private Task deletedTask;

    /**
     * Initializes a command to delete a <code>Task</code> from the to-do list.
     * The task to delete will be identified using its index in the application's
     * <code>TaskList</code>.
     *
     * @param index Index of the task to delete.
     */
    public DeleteCommand(int index) {
        this.index = index;
        this.deletedTask = null;
    }

    /**
     * Returns false as deleting tasks should not terminate the application.
     *
     * @return false
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Deletes a <code>Task</code>, identified by its index, from the input <code>TaskList</code>.
     *
     * @param tasks A collection of <code>Task</code> objects representing the application's state.
     */
    public void execute(TaskList tasks) {
        this.deletedTask = tasks.popTaskByIndex(this.index);
    }

    /**
     * Computes a response to notify the users if a <code>Task</code> is deleted.
     *
     * @param tasks A collection of <code>Task</code> objects representing the application's state.
     * @return A <code>String</code> to respond to the deletion of a <code>Task</code> (if any).
     */
    public String getResponse(TaskList tasks) {
        try {
            if (null == this.deletedTask) {
                throw new TaskNumberNotExistException(this.index);
            }
        } catch (TaskNumberNotExistException e) {
            return e.getMessage();
        }

        return "Noted. I've removed this task:\n" + this.deletedTask.getStatusString();
    }
}

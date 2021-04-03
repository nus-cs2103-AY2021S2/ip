package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;

/**
 * Deletes a task to the taskList.
 */
public class DeleteCommand extends Command {
    private int taskIndexToDelete;

    /**
     * Constructor for the DeleteCommand class.
     *
     * @param taskIndexToDelete an index for the Task object to be deleted.
     */
    public DeleteCommand(int taskIndexToDelete) {
        this.taskIndexToDelete = taskIndexToDelete;
    }

    /**
     * Executes the action of deleting the task.
     *
     * @return the corresponding results to be printed to user.
     */
    @Override
    public String[] execute() {
        String[] executionResult;
        try {
            executionResult = TaskList.deleteTask(taskIndexToDelete);
            Storage.saveDataToStorage();
            return executionResult;
        } catch (DukeException e) {
            return new String[]{e.getMessage()};
        }
    }
}

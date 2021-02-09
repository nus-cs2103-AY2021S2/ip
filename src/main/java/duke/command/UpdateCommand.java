package duke.command;

import java.time.LocalDate;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;

/**
 * Updates the task at given index
 */
public class UpdateCommand extends Command {
    private int taskIndexToUpdate;
    private LocalDate newDate;

    /**
     * Constructor for the DoneCommand class.
     * @param taskIndexToUpdate an index for the Task object to be done
     */
    public UpdateCommand(int taskIndexToUpdate, LocalDate newDate) {
        this.taskIndexToUpdate = taskIndexToUpdate;
        this.newDate = newDate;
    }

    /**
     * Executes the action of updating the task.
     * @return the corresponding results to be printed to user
     */
    @Override
    public String[] execute() {
        String[] executionResult;
        try {
            executionResult = TaskList.updateTaskDate(taskIndexToUpdate, newDate);
            Storage.saveDataToStorage();
            return executionResult;
        } catch (DukeException e) {
            return new String[]{e.getMessage()};
        }
    }
}

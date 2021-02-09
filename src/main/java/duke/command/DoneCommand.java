package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;

/**
 * Complete a task to the taskList.
 */
public class DoneCommand extends Command {
    private int taskIndexToComplete;

    /**
     * Constructor for the DoneCommand class.
     * @param taskIndexToComplete an index for the Task object to be done
     */
    public DoneCommand(int taskIndexToComplete) {
        this.taskIndexToComplete = taskIndexToComplete;
    }

    /**
     * Executes the action of completing the task.
     * @return the corresponding results to be printed to user
     */
    @Override
    public String[] execute() {
        String[] executionResult;
        try {
            executionResult = TaskList.completeTask(taskIndexToComplete);
            Storage.saveDataToStorage();
            return executionResult;
        } catch (DukeException e) {
            return new String[]{e.getMessage()};
        }
    }
}

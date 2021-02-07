package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;

/**
 * Complete a task to the taskList.
 */
public class DoneCommand extends Command {
    private int taskToComplete;

    /**
     * Constructor for the DoneCommand class.
     * @param taskToComplete an index for the Task object to be done
     */
    public DoneCommand(int taskToComplete) {
        this.taskToComplete = taskToComplete;
    }

    /**
     * Executes the action of completing the task.
     * @return the corresponding results to be printed to user
     */
    @Override
    public String[] execute() {
        String[] res;
        try {
            res = TaskList.completeTask(taskToComplete);
            Storage.saveData();
            return res;
        } catch (DukeException e) {
            return new String[]{e.getMessage()};
        }
    }
}

package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Adds a task to the taskList.
 */
public class AddCommand extends Command {
    private Task taskToAdd;

    /**
     * Constructor for the AddCommand class.
     * @param taskToAdd a Task object to be added
     */
    public AddCommand(Task taskToAdd) {
        this.taskToAdd = taskToAdd;
    }

    /**
     * Executes the action of adding the task.
     * @return the corresponding results to be printed to user
     */
    @Override
    public String[] execute() {
        String[] res;
        try {
            res = TaskList.addTask(taskToAdd);
            Storage.saveData();
            return res;
        } catch (DukeException e) {
            return new String[]{e.getMessage()};
        }
    }
}

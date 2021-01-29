package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;

/**
 * ListCommand class which is a type of Command to be executed.
 */

public class ListCommand extends Command {
    String type;

    /**
     * Handles List commands.
     * Triggers the DataHandler to handle the commands.
     *
     * @param t details of the task
     *
     */
    public ListCommand(String t) {
        this.type = t;
    }

    /**
     * Executes the Command in DataHandler.
     *
     * @param tasks list of tasks where this new task is added to
     * @param input details of the task
     * @param storage handles the various tasks according to their type
     */

    public void execute(TaskList tasks, String input, Storage storage) {
            tasks.list();
    }

    /**
     * Checks if it is time to exit Duke.
     */
    public boolean isExit() {
        return false;
    }
}

package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;

/**
 * ByeCommand class which is a type of Command to be executed.
 */
public class ByeCommand extends Command {
    private String userInput;
    private final String line = "------------------------------------------";

    /**
     * Handles Bye commands.
     * Triggers the DataHandler to handle the commands.
     *
     * @param input details of the task
     *
     */
    public ByeCommand(String input) {
        this.userInput = input;

    }

    /**
     * Executes the Command in DataHandler.
     *
     * @param tasks list of tasks where this new task is added to
     * @param input details of the task
     * @param storage handles the various tasks according to their type
     */

    public String execute(TaskList tasks, String input, Storage storage) {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Checks if it is time to exit Duke.
     */
    public boolean isExit() {
        return true;
    }
}

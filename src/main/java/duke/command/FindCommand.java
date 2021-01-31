package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidInstructionException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * ListCommand class which is a type of Command to be executed.
 */

public class FindCommand extends Command {
    private String userInput;

    /**
     * Handles List commands.
     * Triggers the DataHandler to handle the commands.
     *
     * @param input details of the task
     *
     */
    public FindCommand(String input) {
        this.userInput = input;
    }

    /**
     * Executes the Command in DataHandler.
     *
     * @param tasks list of tasks where this new task is added to
     * @param input details of the task
     * @param storage handles the various tasks according to their type
     */

    public String execute(TaskList tasks, String input, Storage storage) throws DukeException {
        try {
            String temp = input.split(" ", 2) [1];
            return tasks.find(input);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidInstructionException();
        }
    }

    /**
     * Checks if it is time to exit Duke.
     */
    public boolean isExit() {
        return false;
    }
}

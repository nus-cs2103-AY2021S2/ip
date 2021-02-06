package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidInstructionException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * ListCommand class which is a type of Command to be executed.
 */

public class UpdateCommand extends Command {
    private String userInput;

    /**
     * Handles Find commands.
     * Triggers the DataHandler to handle the commands.
     *
     * @param input details of the task
     *
     */
    public UpdateCommand(String input) {
        this.userInput = input;
    }

    /**
     * Executes the Command in DataHandler.
     *
     * @param tasks list of tasks where this new task is added to
     * @param input details of the task
     * @param storage handles the various tasks according to their type
     * @throws DukeException in case input is invalid
     */
    public String execute(TaskList tasks, String input, Storage storage) throws DukeException {
        assert !input.isEmpty() : "Input should not be blank.";

        try {
            String taskNumber = input.split(" ") [1];
            return tasks.updateEvent(input);
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

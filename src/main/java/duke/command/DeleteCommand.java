package duke.command;

import duke.exception.DukeException;
import duke.exception.EmptyDeleteException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * DeleteCommand class which is a type of Command to be executed.
 */
public class DeleteCommand extends Command {
    private String userInput;

    /**
     * Handles Delete commands.
     * Triggers the DataHandler to handle the commands.
     *
     * @param input details of the task
     */
    public DeleteCommand(String input) {
        this.userInput = input;
    }

    //    /**
    //     * Returns the user input of command
    //     *
    //     * @return user input
    //     */
    //    public String getUserInput() {
    //        return this.userInput;
    //    }

    /**
     * Executes the Command in DataHandler.
     *
     * @param tasks list of tasks where this new task is added to
     * @param input details of the task
     * @param storage handles the various tasks according to their type
     * @throws DukeException in case there is no input
     */
    public String execute(TaskList tasks, String input, Storage storage) throws DukeException {
        assert !input.isEmpty() : "Input should not be blank.";

        try {
            String temp = input.split(" ", 2)[1];
            return tasks.delete(input);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyDeleteException();
        }
    }

    /**
     * Checks if it is time to exit Duke.
     */
    public boolean isExit() {
        return false;
    }
}

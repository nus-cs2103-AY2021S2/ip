package duke.command;

import java.io.IOException;

import duke.exception.InvalidInstructionException;
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

    /**
     * Returns the user input of command
     *
     * @return user input
     */
    public String getUserInput() {
        return this.userInput;
    }

    /**
     * Executes the Command in DataHandler.
     *
     * @param tasks list of tasks where this new task is added to
     * @param input details of the task
     * @param storage handles the various tasks according to their type
     * @throws IOException in case file is corrupt
     */
    public String execute(TaskList tasks, String input, Storage storage) throws IOException {
        if (Integer.parseInt(input.split(" ")[1]) > tasks.getSize()) {
            new InvalidInstructionException();
            return "";
        } else {
            return tasks.delete(input);
        }
    }

    /**
     * Checks if it is time to exit Duke.
     */
    public boolean isExit() {
        return false;
    }
}

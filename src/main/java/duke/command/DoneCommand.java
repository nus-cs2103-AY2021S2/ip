package duke.command;

import duke.exception.DukeException;
import duke.exception.EmptyDoneException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * DoneCommand class which is a type of Command to be executed.
 */

public class DoneCommand extends Command {
    private String userInput;

    /**
     * Handles Done commands.
     * Triggers the DataHandler to handle the commands.
     *
     * @param input details of the task
     *
     */
    public DoneCommand(String input) {
        this.userInput = input;

    }

    /**
     * Executes the Command in DataHandler.
     *
     * @param tasks list of tasks where this new task is added to
     * @param input details of the task
     * @param storage handles the various tasks according to their type
     * @throws DukeException in case file is corrupt
     */

    public String execute(TaskList tasks, String input, Storage storage) throws DukeException {
        try {
            String temp = input.split(" ", 2)[1];
            return tasks.markDone(input);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyDoneException();
        }
    }

    /**
     * Checks if it is time to exit Duke.
     */
    public boolean isExit() {
        return false;
    }
}

package duke.command;

import duke.exception.InvalidInstructionException;
import duke.storage.Storage;
import duke.task.TaskList;

import java.io.IOException;

/**
 * DoneCommand class which is a type of Command to be executed.
 */

public class DoneCommand extends Command {
    String type;

    /**
     * Handles Done commands.
     * Triggers the DataHandler to handle the commands.
     *
     * @param t details of the task
     *
     */
    public DoneCommand(String t) {
        this.type = t;

    }

    /**
     * Executes the Command in DataHandler.
     *
     * @param tasks list of tasks where this new task is added to
     * @param input details of the task
     * @param storage handles the various tasks according to their type
     * @throws IOException in case file is corrupt
     */

    public void execute(TaskList tasks, String input, Storage storage) throws IOException {
        if (Integer.parseInt(input.split(" ")[1]) > tasks.getSize()) {
            new InvalidInstructionException();
            return;
        } else {
            tasks.markDone(input);
            storage.saveData(tasks);
        }
    }

    /**
     * Checks if it is time to exit Duke.
     */
    public boolean isExit() {
        return false;
    }
}

package duke.command;

import java.io.IOException;

import duke.exception.InvalidInstructionException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * AddCommand class which is a type of Command to be executed.
 */

public class AddCommand extends Command {
    private String userInput;

    /**
     * Handles Todo, Deadline and Event commands.
     * Triggers the DataHandler to handle the commands.
     *
     * @param input details of the task
     *
     */
    public AddCommand(String input) {
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
    public void execute(TaskList tasks, String input, Storage storage) throws IOException {
        String type = input.split(" ")[0];

        if (type.equals("todo")) {
            tasks.addToDo(input);
            storage.saveData(tasks);
        } else if (type.equals("deadline")) {
            tasks.addDeadline(input);
            storage.saveData(tasks);
        } else if (type.equals("event")) {
            tasks.addEvent(input);
            storage.saveData(tasks);
        } else {
            new InvalidInstructionException();
            return;
        }
    }

    /**
     * Checks if it is time to exit Duke.
     */

    public boolean isExit() {
        return false;
    }
}

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
     */

    public void execute(TaskList tasks, String input, Storage storage) {
        if (input.equals("bye")) {
            System.out.println(line);
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println(line);
            return;
        }
    }

    /**
     * Checks if it is time to exit Duke.
     */
    public boolean isExit() {
        return true;
    }
}

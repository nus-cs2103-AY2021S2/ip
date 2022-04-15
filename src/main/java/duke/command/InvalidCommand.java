package duke.command;

import duke.util.MessageFormatter;
import duke.util.TaskList;
import duke.util.TaskStorage;

/**
 * Class representing an Invalid Command.
 */
public class InvalidCommand extends Command {
    private final String errorMessage;

    /**
     * Constructor of the InvalidCommand class.
     *
     * @param errorMessage The error message corresponding to the invalid user command.
     */
    public InvalidCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Prompts user of invalid command.
     *
     * @param tasks List of tasks.
     * @param messageFormatter Formats Duke's response into a String.
     * @param storage Storage of tasks.
     * @return An error message corresponding to the invalid user command.
     */
    public String execute(TaskList tasks, MessageFormatter messageFormatter, TaskStorage storage) {
        return errorMessage;
    }
}

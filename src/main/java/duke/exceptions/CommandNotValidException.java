package duke.exceptions;

/**
 * Error class for invalid commands.
 */
public class CommandNotValidException extends DukeException {
    /**
     * Constructs a CommandNotValidException with specific message for invalid commands.
     */
    public CommandNotValidException() {
        super("Command not valid. Please use \"todo\", \"deadline\""
                + "or \"event\" followed by task description to add new tasks. "
                + "Please use \"list\" to view your list of tasks. "
                + "Please use \"done\" followed by index to mark completed tasks. "
                + "Please use \"delete\" followed by index to delete tasks. "
                + "Please use \"bye\" to exit.");
    }
}

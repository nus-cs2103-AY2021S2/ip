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
                + "or \"event\" followed by task description to add new tasks.\n"
                + "Please use \"list\" to view your list of tasks.\n"
                + "Please use \"done\" followed by index to mark completed tasks.\n"
                + "Please use \"delete\" followed by index to delete tasks.\n"
                + "Please use \"bye\" to exit.");
    }
}

package duke.exception;

/**
 * A class represents a CommandNotFoundException.
 */
public class CommandNotFoundException extends DukeException {
    /**
     * Constructs a CommandNotFoundException.
     * @param message The error message.
     */
    public CommandNotFoundException(String message) {
        super(message);
    }
}

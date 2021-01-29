package duke.exceptions;

/**
 * Exception thrown when command is unknown.
 */
public class UnknownCommandException extends DukeException {
    public UnknownCommandException (String command) {
        super("Sorry I do not understand the command \"" + command + "\" :(");
    }
}

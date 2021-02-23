package chip.exceptions;

/**
 * Exception thrown when command is unknown.
 */
public class UnknownCommandException extends ChipException {
    public UnknownCommandException () {
        super("Sorry I do not understand your command :(");
    }
}

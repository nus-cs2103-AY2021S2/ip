package duke.exception;

/**
 * A class represents a DescriptionMissingException.
 */
public class DescriptionMissingException extends DukeException {
    /**
     * Constructs a DescriptionMissingException.
     * @param message The error message.
     */
    public DescriptionMissingException(String message) {
        super(message);
    }
}

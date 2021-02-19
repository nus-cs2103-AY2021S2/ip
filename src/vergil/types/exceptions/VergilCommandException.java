package vergil.types.exceptions;

/**
 * Represents an exception for the processing of unknown commands.
 */
public class VergilCommandException extends VergilException {
    /**
     * Creates a new command exception.
     * @param   message a details message to include with the exception.
     */
    public VergilCommandException(String message) {
        super(message);
    }
}

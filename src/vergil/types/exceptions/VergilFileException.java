package vergil.types.exceptions;

/**
 * Represents an exception for saving- and loading-from-file operations.
 */
public class VergilFileException extends VergilException {
    /**
     * Creates a new file exception.
     * @param   message a details message to include with the exception.
     */
    public VergilFileException(String message) {
        super(message);
    }
}

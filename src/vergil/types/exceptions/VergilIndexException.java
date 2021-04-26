package vergil.types.exceptions;

/**
 * Represents an exception for using out-of-bounds list numbers to modify tasks.
 */
public class VergilIndexException extends VergilException {
    /**
     * Creates a new index exception with a preset message.
     */
    public VergilIndexException() {
        super("I wasn't able to find a task with the specified number.");
    }

    /**
     * Creates a new index exception with a custom message.
     * @param   message a details message to include with the exception.
     */
    public VergilIndexException(String message) {
        super(message);
    }
}

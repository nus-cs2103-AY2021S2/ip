package vergil.types.exceptions;

/**
 * Represents an exception that is raised by Vergil's operations.
 */
public abstract class VergilException extends Exception {
    /**
     * Creates a new Vergil exception.
     * @param   message a details message to include with the exception.
     */
    public VergilException(String message) {
        super(message);
    }
}

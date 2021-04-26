package vergil.types.exceptions;

/**
 * Represents an exception for the parsing of badly-formatted commands.
 */
public class VergilFormatException extends VergilException {
    /**
     * Creates a new format exception.
     * @param   message a details message to include with the exception.
     */
    public VergilFormatException(String message) {
        super(message);
    }
}

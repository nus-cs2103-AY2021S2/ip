package antonio;

/**
 * Represents an invalid format exception exception that is thrown during an error.
 */
public class InvalidTimeFormatException extends Exception {
    public InvalidTimeFormatException(String errorMessage) {
        super(errorMessage);
    }
}

/**
 * Exception that occurs when the input format is wrong.
 */
public class InvalidTaskFormatException extends Exception {
    /**
     * Constructor for the exception.
     */
    public InvalidTaskFormatException(String message) {
        super(message);
    }

    /**
     * Prints the error message.
     */
    public String printMessage() {
        return super.getMessage();
    }
}

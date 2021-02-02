/**
 * Exception that occurs when the user inputs an unaccepted keyword.
 */
public class InvalidKeywordException extends Exception {
    /**
     * Constructor for the exception.
     */
    public InvalidKeywordException(String message) {
        super(message);
    }

    /**
     * Prints the error message.
     */
    public String printMessage() {
        return super.getMessage();
    }
}

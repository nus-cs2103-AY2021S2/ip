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
    public void printMessage() {
        System.out.println(super.getMessage());
    }
}

/**
 * Exception that occurs when the user does not input a valid number.
 */
public class InvalidNumberException extends Exception {
    /**
     * Constructor for the exception.
     * @param message Error message to be printed out.
     */
    public InvalidNumberException(String message) {
        super(message);
    }

    /**
     * Prints the error message.
     */
    public void printMessage() {
        System.out.println(super.getMessage());
    }
}

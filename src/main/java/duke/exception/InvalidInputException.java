package duke.exception;

/**
 *  An exception raised when user inputs a command that doesn't exist in Duke chat bot.
 */
public class InvalidInputException extends Exception {
    /**
     * Constructs a InvalidInputException
     * @param message  A message that can be displayed when this exception is thrown.
     */
    public InvalidInputException(String message) {
        super(message);
    }
}

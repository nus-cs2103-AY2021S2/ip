package duke.exception;

/**
 * An exception raised when user inputs a command format that doesn't correspond to our commands.
 */
public class InvalidDescriptionException extends Exception {
    /**
     * Constructs an InvalidDescriotionException.
     * @param message   A message that can be displayed when this exception is thrown.
     */
    public InvalidDescriptionException(String message) {
        super(message);
    }
}

package zeke.exceptions;

/**
 * Represents an error which occurs when user enters an invalid index for the done and delete commands.
 */
public class InvalidIndexException extends ZekeException {

    /**
     * Constructor for InvalidIndexException class.
     */
    public InvalidIndexException() {
    }

    @Override
    public String getMessage() {
        return "MonkaS! The index given is out of range and invalid.\n";
    }
}

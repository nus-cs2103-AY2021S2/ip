package zeke.exceptions;

/**
 * Represents an error which occurs when user enters an invalid description of the task.
 */
public class InvalidDescriptionException extends ZekeException {

    /**
     * Constructor for InvalidDescriptionException class.
     */
    public InvalidDescriptionException() {
    }

    @Override
    public String getMessage() {
        return "MonkaS! The description of the task is missing or invalid. Type help for more info\n";
    }
}

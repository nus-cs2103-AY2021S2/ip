package zeke.exceptions;

/**
 * Represents an error which occurs when user enters an invalid date of the task.
 */
public class InvalidDateException extends ZekeException {

    /**
     * Constructor for InvalidDateException class.
     */
    public InvalidDateException() {
    }

    @Override
    public String getMessage() {
        return "MonkaS! The date of the task is invalid. Type help for more info.\n";
    }
}


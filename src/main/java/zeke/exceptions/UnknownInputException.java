package zeke.exceptions;

/**
 * Represents an error which occurs when user enters an unknown input.
 */
public class UnknownInputException extends ZekeException {

    /**
     * Constructor for UnknownInputException class.
     */
    public UnknownInputException() {
    }

    @Override
    public String getMessage() {
        return "MonkaS! I'm sorry, but I don't know what that means.\n";
    }
}

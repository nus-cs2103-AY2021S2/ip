package chadbot.exceptions;

/**
 * The InvalidInputException class is an exception thrown by the TaskList when an invalid input or command is
 * supplied by the user.
 *
 * @author  arsatis
 * @version 1.0
 * @since   2021-01-19
 */
public class InvalidInputException extends Exception {
    /** The message carried by the InvalidInputException. */
    private static final String MESSAGE = "I'm sorry, but I don't know what that means.\n"
            + "Enter \"help\" for more information about Chadbot's functions.";

    /**
     * Default constructor for the InvalidInputException class.
     */
    public InvalidInputException() {
        super(MESSAGE);
    }

    /**
     * Returns the message carried by the InvalidInputException as a String.
     *
     * @return The message carried by the InvalidInputException.
     */
    public static String getExceptionMessage() {
        return MESSAGE;
    }

}

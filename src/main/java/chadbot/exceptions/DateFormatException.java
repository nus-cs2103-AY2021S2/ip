package chadbot.exceptions;

/**
 * The DateFormatException class is an exception thrown by the TaskList when the date specified by the user input
 * is incorrectly formatted.
 *
 * @author  arsatis
 * @version 1.1
 * @since   2021-01-26
 */
public class DateFormatException extends Exception {
    /** The message carried by the DateFormatException. */
    private static final String MESSAGE = "I'm sorry, please give your date in the format YYYY-MM-DD.";

    /**
     * Default constructor for the DateFormatException class.
     */
    public DateFormatException() {
        super(MESSAGE);
    }

}

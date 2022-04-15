package chadbot.exceptions;

/**
 * The TaskTypeErrorException class is an exception thrown by the TaskList the user attempts to modify the date
 * of a task that is neither a deadline nor an event.
 *
 * @author  arsatis
 * @version 1.2
 * @since   2021-02-08
 */
public class TaskTypeErrorException extends Exception {
    /** The message carried by the TaskTypeErrorException. */
    private static final String MESSAGE = "I'm sorry, you can only modify the date of a deadline or an event.";

    /**
     * Default constructor for the TaskTypeErrorException class.
     */
    public TaskTypeErrorException() {
        super(MESSAGE);
    }

}

package chadbot.exceptions;

/**
 * The LoadFailureException class is an exception thrown by the TaskList when Duke fails to add the data from
 * the saved file into the task list.
 *
 * @author  arsatis
 * @version 1.2
 * @since   2021-02-08
 */
public class LoadFailureException extends Exception {
    /** The message carried by the DateFormatException. */
    private static final String MESSAGE = "Something went wrong during the loading of your save file.";

    /**
     * Default constructor for the LoadFailureException class.
     */
    public LoadFailureException() {
        super(MESSAGE);
    }

}

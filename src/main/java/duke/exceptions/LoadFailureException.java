package duke.exceptions;

/**
 * The LoadFailureException class is an exception thrown by the TaskList when Duke fails to add the data from
 * the saved file into the task list.
 *
 * @author  arsatis
 * @version 1.2
 * @since   2021-02-08
 */
public class LoadFailureException extends Exception {

    /**
     * Default constructor for the LoadFailureException class.
     */
    public LoadFailureException() {
        super("Something went wrong during the loading of your save file.");
    }

}

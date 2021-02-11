package chadbot.exceptions;

/**
 * The ListOutOfBoundsException class is an exception thrown by the TaskList when the user attempts to mark a
 * task that is not in the list as done, or delete a task that is not in the list.
 *
 * @author  arsatis
 * @version 1.0
 * @since   2021-01-19
 */
public class ListOutOfBoundsException extends Exception {

    /**
     * Default constructor for the ListOutOfBoundsException class.
     *
     * @param size The current size of the list in a TaskList object.
     */
    public ListOutOfBoundsException(int size) {
        super("I'm sorry, the list currently only has " + size + " elements.");
    }

}

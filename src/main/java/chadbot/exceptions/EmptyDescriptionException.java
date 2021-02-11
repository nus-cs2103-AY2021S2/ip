package chadbot.exceptions;

/**
 * The EmptyDescriptionException class is an exception thrown by the TaskList when a description is not supplied
 * for a task by the user input.
 *
 * @author  arsatis
 * @version 1.0
 * @since   2021-01-19
 */
public class EmptyDescriptionException extends Exception {

    /**
     * Default constructor for the EmptyDescriptionException class.
     *
     * @param s The type of task which triggered this exception.
     */
    public EmptyDescriptionException(String s) {
        super("I'm sorry, the description of a " + s + " cannot be empty.");
    }

}

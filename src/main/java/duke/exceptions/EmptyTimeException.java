package main.java.duke.exceptions;

/**
 * The EmptyTimeException class is an exception thrown
 * by the Task Manager when a time is not supplied for a
 * deadline or event by the user input.
 *
 * @author  arsatis
 * @version 1.0
 * @since   2021-01-19
 */
public class EmptyTimeException extends Exception {

    /**
     * Default constructor for the EmptyTimeException class.
     *
     * @param s The type of task which triggered this exception.
     */
    public EmptyTimeException(String s) {
        super("OOPS!!! The time of a " + s + " cannot be empty.");
    }

}

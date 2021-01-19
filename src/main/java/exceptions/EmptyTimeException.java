package main.java.exceptions;

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

    public EmptyTimeException(String s) {
        super("OOPS!!! The time of a " + s + " cannot be empty.");
    }

}

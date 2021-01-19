package main.java.exceptions;

/**
 * The EmptyDescriptionException class is an exception thrown
 * by the Task Manager when a description is not supplied for a
 * task by the user input.
 *
 * @author  arsatis
 * @version 1.0
 * @since   2021-01-19
 */
public class EmptyDescriptionException extends Exception {

    public EmptyDescriptionException(String s) {
        super("OOPS!!! The description of a " + s + " cannot be empty.");
    }

}

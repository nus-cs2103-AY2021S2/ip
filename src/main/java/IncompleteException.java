package main.java;

/*
 * A type of DukeException.
 * An exception for incomplete commands.
 */
public class IncompleteException extends DukeException {
    public IncompleteException(String error) {
        super(error);
    }
}

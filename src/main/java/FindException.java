package main.java;

/*
 * A type of DukeException.
 * An exception for when the keyword is not found in the task-list.
 */
public class FindException extends DukeException {
    public FindException(String error) {
        super(error);
    }
}
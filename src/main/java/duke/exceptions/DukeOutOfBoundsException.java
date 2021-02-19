package duke.exceptions;

/**
 * An exception that is thrown when the program tries to access an invalid positon in the TaskList.
 */
public class DukeOutOfBoundsException extends Exception {
    public DukeOutOfBoundsException(String message) {
        super(message);
    }
}

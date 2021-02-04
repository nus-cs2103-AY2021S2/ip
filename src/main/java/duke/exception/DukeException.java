package duke.exception;

/**
 * Signals to the user that the program has encounter an error based on his/her input.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}

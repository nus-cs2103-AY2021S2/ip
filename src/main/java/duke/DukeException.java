package duke;

/**
 * Exception class for any errors produced in Duke.
 */
public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}

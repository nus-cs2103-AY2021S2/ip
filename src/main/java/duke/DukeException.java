package duke;

/**
 * General Exception Handler specific to Duke.
 */
public class DukeException extends Exception {
    private static final long serialVersionUID = 1L;

    DukeException(String message) {
        super(message);
    }
}

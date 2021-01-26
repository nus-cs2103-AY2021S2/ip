package duke.exception;

/**
 * Base exception class of Duke
 */
public class DukeException extends RuntimeException {
    public DukeException(String msg) {
        super(msg);
    }
}

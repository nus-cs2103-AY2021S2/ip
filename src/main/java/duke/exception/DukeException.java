package duke.exception;

/**
 * Class representing Exceptions in Duke.
 */
public class DukeException extends Exception {
    /**
     * Constructor of DukeException.
     *
     * @param errorMsg Error message to prompt user of error.
     */
    protected DukeException(String errorMsg) {
        super(errorMsg);
    }
}

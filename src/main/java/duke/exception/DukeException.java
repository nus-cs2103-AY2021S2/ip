package duke.exception;

public class DukeException extends Exception {

    /**
     * Constructor for duke.DukeExceptions.DukeException.
     * @param errorMsg Error message to prompt user of error.
     */

    protected DukeException(String errorMsg) {
        super(errorMsg);
    }
}

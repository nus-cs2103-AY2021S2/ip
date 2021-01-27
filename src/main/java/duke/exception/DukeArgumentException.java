package duke.exception;

/**
 * Class representing an Invalid Argument Exception in Duke, sub-class of DukeException.
 */
public class DukeArgumentException extends DukeException {
    /**
     * Constructor of DukeArgumentException.
     *
     * @param errorMsg Error Message prompting user of invalid argument.
     */
    public DukeArgumentException(String errorMsg) {
        super(errorMsg);
    }
}

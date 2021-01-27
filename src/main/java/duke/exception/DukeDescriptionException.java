package duke.exception;

public class DukeDescriptionException extends DukeException {

    /**
     * Constructor for duke.DukeExceptions.DukeDescriptionException.
     *
     * @param errorMsg Error message to prompt user to enter a description for the task.
     */

    public DukeDescriptionException(String errorMsg) {
        super(errorMsg);
    }
}

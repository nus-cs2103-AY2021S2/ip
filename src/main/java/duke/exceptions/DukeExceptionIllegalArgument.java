package duke.exceptions;

/**
 * Thrown if wrong user input is supplied.
 */
public class DukeExceptionIllegalArgument extends DukeException {

    public DukeExceptionIllegalArgument(String description) {
        super(description);
    }
}

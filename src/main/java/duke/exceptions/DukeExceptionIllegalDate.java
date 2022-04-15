package duke.exceptions;

/**
 * Thrown if wrong date format is supplied by user.
 */
public class DukeExceptionIllegalDate extends DukeException {

    public DukeExceptionIllegalDate(String description) {
        super(description);
    }
}

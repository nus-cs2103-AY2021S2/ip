package duke.exceptions;

/**
 * Thrown if task loaded from database file cannot be parsed.
 */
public class DukeExceptionInvalidTaskString extends DukeException {

    public DukeExceptionInvalidTaskString(String description) {
        super(description);
    }
}

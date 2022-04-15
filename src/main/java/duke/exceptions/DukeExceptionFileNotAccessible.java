package duke.exceptions;

/**
 * Thrown if task database file cannot be accessed.
 */
public class DukeExceptionFileNotAccessible extends DukeException {

    public DukeExceptionFileNotAccessible(String description) {
        super(description);
    }
}

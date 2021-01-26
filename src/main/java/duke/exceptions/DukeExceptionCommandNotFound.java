package duke.exceptions;

/**
 * Thrown if Duke command cannot be parsed.
 */
public class DukeExceptionCommandNotFound extends DukeException {

    public DukeExceptionCommandNotFound(String description) {
        super(description);
    }
}
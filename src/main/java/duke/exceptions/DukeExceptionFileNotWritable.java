package duke.exceptions;

/**
 * Thrown if task database file cannot be written to.
 */
public class DukeExceptionFileNotWritable extends DukeException {

    public DukeExceptionFileNotWritable(String description) {
        super(description);
    }
}

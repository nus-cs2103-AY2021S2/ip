package duke.exceptions;

/**
 * General exception class for Duke.
 *
 * All exceptions thrown by Duke should inherit from this class.
 */
public class DukeException extends Exception {

    protected String description;

    public DukeException(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}

package duke;

/**
 * Abstracts away exceptions arising due to Duke.
 */
public class DukeException extends Exception {
    public DukeException(String error) {
        super(error);
    }
}

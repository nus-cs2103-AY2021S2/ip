package exception;

/**
 * duke.Duke missing task exception.
 */
public class DukeMissingTaskException extends DukeException {
    /**
     * Instantiates a new duke.Duke missing task exception.
     */
    public DukeMissingTaskException() {
        super("Oops! task.Task not found, please try again.");
    }
}

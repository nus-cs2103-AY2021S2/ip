package duke.exceptions;

/**
 * Error class for invalid start and end time of task.
 */
public class InvalidTimeDurationException extends DukeException {
    /**
     * Constructs a CommandNotValidException with specific message
     * for invalid start and end time of task.
     */
    public InvalidTimeDurationException() {
        super("\tPlease enter a valid start and end time duration\n\t(start time < end time).\n");
    }
}

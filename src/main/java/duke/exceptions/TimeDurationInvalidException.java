package duke.exceptions;

/**
 * Error class for invalid start and end time of task.
 */
public class TimeDurationInvalidException extends DukeException {
    /**
     * Constructs a TimeDurationInvalidException with specific message
     * for invalid start and end time of task.
     */
    public TimeDurationInvalidException() {
        super("Please enter a valid start and end time duration (start time < end time).");
    }
}

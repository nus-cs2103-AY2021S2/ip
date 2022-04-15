package duke.dukeexceptions;

/**
 * Models the exception to be thrown when there is an invalid date and time format input.
 */
public class InvalidDateTimeException extends DukeException {
    /**
     * Constructs an InvalidDateTimeException to tell the user that the input date format is not recognised by the
     * system.
     */
    public InvalidDateTimeException() {
        super("Please enter your date in one of the following formats:\n"
                + "d/M/yyyy HHmm OR d MMM yy HHmm OR dd-MM-yy HHmm");
    }
}

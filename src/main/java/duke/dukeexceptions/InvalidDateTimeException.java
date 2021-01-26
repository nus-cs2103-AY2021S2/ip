package duke.dukeexceptions;

/**
 * Custom Exception class to specify invalid date format input by the user.
 */
public class InvalidDateTimeException extends DukeException {
    public InvalidDateTimeException() {
        super("Please enter your date in one of the following formats:\n"
                + "d/M/yyyy HHmm OR d MMM yy HHmm OR dd-MM-yy HHmm");
    }
}

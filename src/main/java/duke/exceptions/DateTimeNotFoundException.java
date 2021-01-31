package duke.exceptions;

/**
 * Error class for no indication of date and time.
 */
public class DateTimeNotFoundException extends DukeException {
    /**
     * Constructs a DateTimeNotFoundException with specific message for
     * no indication of date and time.
     */
    public DateTimeNotFoundException() {
        super("Please enter the date (DD/MM/YYYY) with optional "
                + "time (in 24 hours format) after \"/by\" for Deadline Tasks "
                + "or date with optional start and end time after \"/at\""
                + "for Event Tasks.");
    }
}

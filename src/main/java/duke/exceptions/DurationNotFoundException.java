package duke.exceptions;

/**
 * Error class for no duration input.
 */
public class DurationNotFoundException extends DukeException {
    private static String message = "Please enter duration of task in minutes (whole number) after \"/within\".";
    /**
     * Constructs a DurationNotFoundException with specific message for no duration input.
     */
    public DurationNotFoundException() {
        super(message);
    }
}

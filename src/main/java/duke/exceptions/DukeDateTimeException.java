package duke.exceptions;

public class DukeDateTimeException extends DukeException {
    private String message;

    /**
     * Constructor for DukeException for invalid date time format.
     */
    public DukeDateTimeException() {
        super("This is a Duke DateTime exception");
        message = super.getMessage() + " "
                + "Your date/time description\n"
                + "does not match the dd.MMM.yyyy HH:mm format.";
    }

    /**
     * Returns message of invalid date time format.
     * @return Message of invalid date time format.
     */
    @Override
    public String getMessage() {
        return message;
    }
}

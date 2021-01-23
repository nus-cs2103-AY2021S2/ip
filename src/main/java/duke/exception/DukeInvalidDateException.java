package duke.exception;

public class DukeInvalidDateException extends DukeException {
    /**
     * DukeInvalidDateFormatException constructor.
     */
    public DukeInvalidDateException() {
    }

    @Override
    public String getMessage() {
        return "Invalid date format provided. "
                + "Accepted format: ddmmyyyy.";
    }

    @Override
    public String toString() {
        return "    Invalid date format provided.";
    }
}

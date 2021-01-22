package exceptions;

public class DukeInvalidDateFormatException extends DukeException {
    /**
     *  DukeInvalidDateFormatException constructor.
     */
    public DukeInvalidDateFormatException() {
    }

    @Override
    public String getMessage() {
        return "Invalid date format provided. " +
                "Accepted format: ddmmyyyy.";
    }

    @Override
    public String toString() {
        return "    Invalid date format provided.";
    }
}

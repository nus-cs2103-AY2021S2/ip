package jaryl.duke;

/**
 * InvalidFormatException represents a DukeException.
 * Handles invalid format exceptions
 */
public class InvalidFormatException extends DukeException {
    /**
     * Constructor to instantiate a new EmptyListException
     * @params msg  error message
     */
    public InvalidFormatException(String msg) {
        super(msg);
    }
}
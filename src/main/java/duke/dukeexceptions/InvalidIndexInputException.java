package duke.dukeexceptions;

/**
 * Models the exception to be thrown when the user sends an invalid index for parsing.
 */
public class InvalidIndexInputException extends DukeException {
    public InvalidIndexInputException(String message) {
        super(message);
    }
}

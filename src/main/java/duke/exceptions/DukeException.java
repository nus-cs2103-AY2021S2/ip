package duke.exceptions;

/**
 * Serves as the parent exception class for all exceptions
 * in the Duke application.
 */
public class DukeException extends Exception {
    public DukeException(String error) {
        super(error);
    }

    @Override
    public String toString() {
        return "\nMaster, I'm afraid I don't understand what you mean.";
    }
}

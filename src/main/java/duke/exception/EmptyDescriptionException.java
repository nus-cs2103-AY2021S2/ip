package duke.exception;

public class EmptyDescriptionException extends Exception {
    /**
     * Creates a new EmptyDescriptionException.
     * @param msg
     */
    public EmptyDescriptionException(String msg) {
        super("OOPS!!! The description of a(n) " + msg + " cannot be empty.");
    }
}

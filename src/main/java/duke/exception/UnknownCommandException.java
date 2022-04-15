package duke.exception;

public class UnknownCommandException extends Exception {
    /**
     * Creates a new UnknownCommandException.
     */
    public UnknownCommandException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}

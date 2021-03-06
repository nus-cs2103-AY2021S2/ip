package duke.exception;

/**
 * exception in the case when the command from user is unknown
 */
public class UnknownCommandException extends DukeException {
    private static final String ERROR_MESSAGE = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";

    public UnknownCommandException() {
        super(ERROR_MESSAGE);
    }
}

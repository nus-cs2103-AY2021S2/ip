package duke.dukeexceptions;

/**
 * Models an exception to be thrown when a command is not followed by a required argument.
 */
public class EmptyArgumentException extends DukeException {
    public EmptyArgumentException(String message) {
        super(message);
    }
}

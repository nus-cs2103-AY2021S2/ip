package duke.exceptions;

/**
 * Error class for no selection of task.
 */
public class InvalidTaskSelectionException extends DukeException {
    /**
     * Constructs a CommandNotValidException with specific message
     * for no selection of task.
     */
    public InvalidTaskSelectionException() {
        super("\tPlease enter task number after command.\n");
    }
}

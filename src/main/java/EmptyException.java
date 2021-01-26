package duke;

/**
 * Encapsulates information and state of an EmptyException.
 * Arises when user enters task type without description.
 */
public class EmptyException extends DukeException {
    /** Fixed message to remind user to include task description. */
    static final String message = ":( OOPS! the description of a task cannot be empty.";

    /**
     * Initialises new empty description exception.
     */
    public EmptyException() {
        super(message);
    }
}

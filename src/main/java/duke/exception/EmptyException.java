package duke.exception;

/**
 * Encapsulates information and state of an EmptyException.
 * Arises when user enters task type without description.
 */
public class EmptyException extends DukeException {
    /** Fixed message to remind user to include task description. */
    static final String MESSAGE = ":( hmm I'm not sure what you want to do. type help for tips!";

    /**
     * Initialises new empty description exception.
     */
    public EmptyException() {
        super(MESSAGE);
    }
}

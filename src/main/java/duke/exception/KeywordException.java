package duke.exception;

/**
 * Encapsulates information and state of an EmptyException.
 * Arises when user enters words that do not correspond to any command.
 */
public class KeywordException extends DukeException {
    /** Fixed message to remind user to include keyword. */
    static final String MESSAGE = "type \"by\" (for deadline) or \"at\" (for event) before datetime";

    /**
     * Initialises new missing keyword exception.
     */
    public KeywordException() {
        super(MESSAGE);
    }
}

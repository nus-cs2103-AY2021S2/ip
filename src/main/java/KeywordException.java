package duke;

/**
 * Encapsulates information and state of an EmptyException.
 * Arises when user enters words that do not correspond to any command.
 */
public class KeywordException extends DukeException {
    /** Fixed message to remind user to include keyword. */
    static final String message = ":( oops sorry I don't know what that means.";

    /**
     * Initialises new missing keyword exception.
     */
    public KeywordException() {
        super(message);
    }
}

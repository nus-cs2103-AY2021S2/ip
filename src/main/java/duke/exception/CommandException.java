package duke.exception;

/**
 * Encapsulates information and state of an EmptyException.
 * Arises when user enters words that do not correspond to any command.
 */
public class CommandException extends DukeException {
    /** Fixed message to remind user to include keyword. */
    static final String MESSAGE = ":( oops sorry I don't know what that means. type help for tips!";

    /**
     * Initialises new missing keyword exception.
     */
    public CommandException() {
        super(MESSAGE);
    }
}

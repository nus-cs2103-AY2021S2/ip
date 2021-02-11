/**
 * Represents an exception which occurs when a command is incomplete.
 */
public class IncompleteCommandException extends Exception {

    /**
     * Constructor for IncompleteCommandException.
     */
    IncompleteCommandException() {
        super("The description cannot be empty.");
    }
}

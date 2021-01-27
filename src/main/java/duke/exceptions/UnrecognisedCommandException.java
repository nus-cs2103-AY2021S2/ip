package duke.exceptions;

/**
 * Represents an exception thrown when an invalid command is provided.
 */
public class UnrecognisedCommandException extends DukeException {

    /**
     * Constructor for UnrecognisedCommandException.
     * @param s Command name as a String.
     */
    public UnrecognisedCommandException(String s) {
        super("You have entered \"" + s + "\", which is an unrecognised command. Please try again!");
    }
}

package duke.exception;

/**
 * Exception where user keys in alphabet when he is supposed to key in a number.
 */

public class AlphabetsInsteadOfNumberException extends DukeException {

    /**
     * Method to throw the Exception.
     */
    public AlphabetsInsteadOfNumberException() {
        super("I'm sorry, but you keyed in an alphabet.\n"
                + "You can only delete the number from the list.");
    }
}

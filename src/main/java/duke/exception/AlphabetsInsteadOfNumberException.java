package duke.exception;

/**
 * Exception where user keys in alphabet when he is supposed to key in a number.
 */

public class AlphabetsInsteadOfNumberException {

    /**
     * Method to throw the Exception.
     */
    public AlphabetsInsteadOfNumberException() {
        System.out.println("------------------------------------------\n"
                + "☹ OOPS!!! I'm sorry, but you keyed in an alphabet. "
                + "You can only delete a number from the list.\n"
                + "------------------------------------------");
    }
}

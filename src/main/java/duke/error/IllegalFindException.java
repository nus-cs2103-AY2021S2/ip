package duke.error;

/**
 * Exception thrown when 'find' input is incorrectly formatted.
 *
 * @author  Nicole Ang
 */
public class IllegalFindException extends StringIndexOutOfBoundsException {
    /**
     * Constructs a new IllegalFindException.
     *
     * @param message  Exception message.
     */
    public IllegalFindException(String message) {
        super(message);
    }

    /**
     * Returns error message telling user that they did not enter the search keyword and to re-enter the input.
     *
     * @return Error message.
     */
    @Override
    public String toString() {
        return "You forgot to enter the keyword to search for!\n" + "Please re-enter!";
    }
}

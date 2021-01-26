package duke.error;

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
        return "You forgot to enter the keyword to search for!\n"
                + (char) 9 + (char) 9 + "Please re-enter!";
    }
}

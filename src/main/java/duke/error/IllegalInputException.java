package duke.error;

public class IllegalInputException extends IllegalArgumentException {
    /**
     * Constructs a new IllegalInputException.
     *
     * @param message  Exception message.
     */
    IllegalInputException(String message) {
        super(message);
    }

    /**
     * Returns error message telling user that they entered an invalid input and to re-enter the input.
     *
     * @return Error message.
     */
    @Override
    public String toString() {
        return "I don't understand what you just entered >.<\n" + "Please re-enter!";
    }
}

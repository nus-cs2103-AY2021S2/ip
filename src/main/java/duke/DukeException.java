package duke;

/**
 * Customised exception for Dukebot.
 */
public class DukeException extends Exception {
    // Contains details on why the exception was thrown.
    private final String errorMessage;

    /**
     * The constructor takes in an argument which corresponds to the message to be displayed
     * when an exception is created.
     *
     * @param errorMessage that contains details on why the exception was thrown.
     */
    public DukeException(String errorMessage) {
        assert !errorMessage.equals("") : "error message should not be empty";
        this.errorMessage = errorMessage;
    }

    /**
     * When printed, the exception will return the error message.
     *
     * @return the error message.
     */
    @Override
    public String toString() {
        return this.errorMessage;
    }
}

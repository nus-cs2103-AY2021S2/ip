package duke;

/**
 * Customised exception for Dukebot.
 */
public class DukeException extends Exception {
    private final String errorMessage;

    /**
     * The constructor takes in an argument which corresponds to the message to be displayed
     * when an exception is created.
     *
     * @param error the error that causes the exception
     */
    public DukeException(String error) {
        this.errorMessage = error;
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

package duke;

/**
 * Exception class to handle errors, especially user input errors.
 */
public class DukeException extends Exception{
    /**
     * Constructs an exception with an error message.
     *
     * @param errorMessage Error message.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Returns the message of the exception.
     * @return Message of the exception.
     */
    public String toString() {
        return super.getMessage();
    }
}

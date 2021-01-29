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

    public String toString() {
        return super.getMessage();
    }
}

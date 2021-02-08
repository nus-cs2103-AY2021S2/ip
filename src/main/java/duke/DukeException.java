package duke;

/**
 * Exception thrown when there is an invalid input from the user.
 */
public class DukeException extends Exception {
    /**
     * Constructor method
     * @param error The error message.
     */
    public DukeException(String error) {
        super(error);
    }

}

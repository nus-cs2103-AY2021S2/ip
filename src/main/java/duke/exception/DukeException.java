package duke.exception;

/**
 * Exception class for project duke.
 */
public class DukeException extends Exception {
    /**
     * Constructor.
     * @param errorMessage specific error message for the exception created
     */
    public DukeException(String errorMessage) {
        super("Oh no :( " + errorMessage);
    }
}

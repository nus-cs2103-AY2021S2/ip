package duke.exception;

/**
 * The TaskException class is used to handle exceptions that occur due to input
 * errors
 */
public class TaskException extends Exception {

    private static final long serialVersionUID = 1L;

    public TaskException(String message) {
        super(message);
    }
}

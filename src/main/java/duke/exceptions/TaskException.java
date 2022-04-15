package duke.exceptions;

/**
 * Task Exception, which is the exception Tasks will throw
 */

public class TaskException extends Exception {
    /**
     * Constructor for TaskException
     *
     * @param message Error message regarding the execption
     */
    public TaskException(String message) {
        super(message);
    }
}

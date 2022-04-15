package ekud.common.exception;

/**
 * Exception to be thrown when there are no known tasks.
 */
public class NoTasksException extends EkudException {

    public NoTasksException() {
        super(Messages.NO_TASK_EXCEPTION_TEXT);
    }
}

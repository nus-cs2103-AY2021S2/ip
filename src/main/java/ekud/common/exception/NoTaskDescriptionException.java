package ekud.common.exception;

/**
 * Exception to be thrown when the task description is not given.
 */
public class NoTaskDescriptionException extends EkudException {

    public NoTaskDescriptionException() {
        super(Messages.NO_TASK_DESCRIPTION_EXCEPTION_TEXT);
    }
}

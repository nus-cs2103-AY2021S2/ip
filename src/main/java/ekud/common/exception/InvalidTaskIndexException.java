package ekud.common.exception;

/**
 * Exception thrown when the given task index is out of range.
 */
public class InvalidTaskIndexException extends EkudException {

    public InvalidTaskIndexException() {
        super(Messages.INVALID_TASK_INDEX_EXCEPTION_TEXT);
    }
}

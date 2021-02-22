package zeke.exceptions;

/**
 * Represents an error which occurs when user attempts to mark an already completed task as completed.
 */
public class CompletedTaskException extends ZekeException {

    public CompletedTaskException() {
    }

    @Override
    public String getMessage() {
        return "MonkaS! The task is already completed.\n";
    }
}

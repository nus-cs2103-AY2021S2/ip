package duke.task;

/**
 * Represents a failure to parse a task.
 */
public class TaskParseException extends Exception {
    public TaskParseException(String message) {
        super(message);
    }

    /**
     * Returns the message of this exception.
     *
     * @return The message of this exception.
     */
    @Override
    public String toString() {
        return getMessage();
    }
}

package yoda.task;

/**
 * InvalidTaskListIndexException class that throws an exception when index of task does not exist in TaskList.
 */
public class InvalidTaskListIndexException extends Exception {
    /**
     * Creates an InvalidTaskListIndexException exception.
     * @param error The error the user made when entering their command.
     */
    public InvalidTaskListIndexException(String error) {
        super(error);
    }
}

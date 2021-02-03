package CustomExceptions;

public class TaskNumberDoesNotExistException extends Exception {

    public TaskNumberDoesNotExistException(int taskNumber) {
        super("Task " + taskNumber + " does not exist :O");
    }
}

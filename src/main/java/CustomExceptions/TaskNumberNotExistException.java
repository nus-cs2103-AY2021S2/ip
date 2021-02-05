package CustomExceptions;

public class TaskNumberNotExistException extends Exception {

    public TaskNumberNotExistException(int taskNumber) {
        super("Task " + taskNumber + " does not exist :O");
    }
}

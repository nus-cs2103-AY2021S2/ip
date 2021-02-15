package todobeast.exceptions;

public class TaskNotFoundException extends ToDoBeastException {
    public TaskNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}

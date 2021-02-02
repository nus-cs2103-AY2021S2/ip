package main.java.exceptions;

public class TaskDoesNotExistException extends Exception {
    public TaskDoesNotExistException() {
        super("    The task your specified does not exist. Please retype.");
    }
}

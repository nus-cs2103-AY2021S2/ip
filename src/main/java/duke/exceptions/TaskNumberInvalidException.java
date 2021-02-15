package duke.exceptions;

public class TaskNumberInvalidException extends Exception {

    public TaskNumberInvalidException() {
        super("The input that follows a 'done' action should be a positive integer! :O");
    }
}

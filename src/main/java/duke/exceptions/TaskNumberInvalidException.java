package duke.exceptions;

public class TaskNumberInvalidException extends Exception {

    public TaskNumberInvalidException() {
        super("The description that follows need to be a positive integer meow!!");
    }
}

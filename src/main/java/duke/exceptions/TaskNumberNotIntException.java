package duke.exceptions;

public class TaskNumberNotIntException extends Exception {

    public TaskNumberNotIntException() {
        super("The input that follows a 'done' action should be an integer! :D");
    }
}

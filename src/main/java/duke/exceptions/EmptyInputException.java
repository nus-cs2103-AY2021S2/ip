package duke.exceptions;

public class EmptyInputException extends Exception {

    public EmptyInputException() {
        super("You need to enter some input...");
    }
}

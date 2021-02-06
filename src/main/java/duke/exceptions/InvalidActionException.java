package duke.exceptions;

public class InvalidActionException extends Exception {

    public InvalidActionException(String action) {
        super("OOPS!!! I'm sorry, but I don't know what '" + action + "' means :-(");
    }
}

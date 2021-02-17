package duke.exceptions;

public class InvalidActionException extends Exception {

    public InvalidActionException(String action) {
        super("I don't know what '" + action + "' means... O.O");
    }
}

package exceptions;

public class MissingInputException extends DukeException{
    public MissingInputException(String action) {
        super("     ☹ OOPS!!! The description of a " + action + " cannot be empty.");
    }
}

package exceptions;

public class AddMissingInputException extends DukeException{
    public AddMissingInputException(String action) {
        super("     ☹ OOPS!!! The description of a " + action + " cannot be empty.");
    }
}

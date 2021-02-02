package duke.exceptions;

public class UnknownInputException extends DukeException {
    public UnknownInputException(String type) {
        super("☹ OOPS!!! The description of a "
                + type + " function cannot be empty.");
    }
}

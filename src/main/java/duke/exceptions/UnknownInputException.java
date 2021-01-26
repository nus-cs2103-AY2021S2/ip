package duke.exceptions;

public class UnknownInputException extends DukeException {
    public UnknownInputException() {
        super("Sumimasen but I cannot understand what you just said.");
    }
}

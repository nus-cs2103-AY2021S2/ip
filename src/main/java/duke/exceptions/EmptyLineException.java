package duke.exceptions;

public class EmptyLineException extends DukeException {
    public EmptyLineException(String message) {
        super("☹ OOPS!!! The input line cannot be empty.");
    }
}

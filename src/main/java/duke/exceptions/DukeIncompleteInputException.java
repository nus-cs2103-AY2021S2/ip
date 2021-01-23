package duke.exceptions;

public class DukeIncompleteInputException extends DukeException {
    public DukeIncompleteInputException(String action) {
        super(action);
    }
}

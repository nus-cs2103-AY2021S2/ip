package duke.exceptions;

public class InvalidDescriptionException extends DukeException {

    public InvalidDescriptionException() {
    }

    @Override
    public String getMessage() {
        return "MonkaS! The description of the task is missing or invalid.\n";
    }
}

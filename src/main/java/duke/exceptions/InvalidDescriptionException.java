package duke.exceptions;

public class InvalidDescriptionException extends DukeException {

    public InvalidDescriptionException() {
    }

    @Override
    public String getMessage() {
        return "OOPS!!! The description of the task is invalid or missing.\n";
    }
}

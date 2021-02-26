package duke.exceptions;

public class TaskSelectionInvalidException extends DukeException {
    public TaskSelectionInvalidException() {
        super("Please enter task number after command.");
    }
}

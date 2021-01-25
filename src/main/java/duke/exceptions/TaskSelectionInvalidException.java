package duke.exceptions;

public class TaskSelectionInvalidException extends DukeException {
    public TaskSelectionInvalidException() {
        super("\tPlease enter task number after command.\n");
    }
}

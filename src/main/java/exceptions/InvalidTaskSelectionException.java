package exceptions;

public class InvalidTaskSelectionException extends DukeException {
    public InvalidTaskSelectionException() {
        super("\tPlease enter task number after command.\n");
    }
}

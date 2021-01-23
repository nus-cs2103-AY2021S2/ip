//public class InvalidTaskSelectionException extends Exception {
public class InvalidTaskSelectionException extends DukeException {
    public InvalidTaskSelectionException() {
        super("\tPlease enter task number after command.\n");
    }
}

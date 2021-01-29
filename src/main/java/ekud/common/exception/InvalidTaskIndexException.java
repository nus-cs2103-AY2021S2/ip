package ekud.common.exception;

public class InvalidTaskIndexException extends DukeException {
    public InvalidTaskIndexException() {
        super("Invalid index for task selection!");
    }
}

package ekud.common.exception;

public class InvalidTaskIndexException extends EkudException {
    public InvalidTaskIndexException() {
        super("Invalid index for task selection!");
    }
}

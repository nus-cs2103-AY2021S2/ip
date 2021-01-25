public class EmptyArgumentException extends DukeException {
    public EmptyArgumentException(String message) {
        super(message);
    }
    public EmptyArgumentException(String message, Throwable cause) {
        super(message, cause);
    }
}
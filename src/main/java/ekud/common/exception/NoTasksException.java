package ekud.common.exception;

public class NoTasksException extends EkudException {
    public NoTasksException() {
        super("Hmm, you got nothing better to do.");
    }
}

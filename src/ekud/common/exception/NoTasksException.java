package ekud.common.exception;

public class NoTasksException extends DukeException {
    public NoTasksException() {
        super("Hmm, you got nothing better to do.");
    }
}

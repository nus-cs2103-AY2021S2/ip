package dbot.exception;

public class DukeException extends Exception {
    private String message;

    public DukeException(String message) {
        super(message);
    }

    public DukeException(Throwable cause) {
        super(cause);
    }

}

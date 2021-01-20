package exception;

public class DukeException extends Exception {
    private static final long serialVersionUID = 1L;

    public DukeException() {
        super();
    }

    public DukeException(String message) {
        super(message);
    }
}

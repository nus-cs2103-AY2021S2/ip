import java.lang.Exception;

public class DukeException extends Exception {
    DukeException() {
        super();
    }

    DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}

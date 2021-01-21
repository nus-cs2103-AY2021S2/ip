package duke;

public class DukeException extends RuntimeException {
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}

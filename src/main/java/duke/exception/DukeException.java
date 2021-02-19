package duke.exception;

public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    public DukeException() {
    }

    @Override
    public String getMessage() {
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}

package duke.exception;

public class InvalidToDoCommandException extends DukeException {
    public InvalidToDoCommandException() {
        super();
    }

    @Override
    public String getMessage() {
        return " ☹ OOPS!!! invalid usage of todo command.\nFormat: todo <description>";
    }
}

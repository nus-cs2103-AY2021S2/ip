package duke.exception;

public class InvalidEventCommandException extends DukeException {
    public InvalidEventCommandException() {
        super();
    }

    @Override
    public String getMessage() {
        return " ☹ OOPS!!! invalid usage of Event command.\nFormat: event <description> /at <yyyy-MM-dd>";
    }
}

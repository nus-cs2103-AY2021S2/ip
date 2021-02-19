package duke.exception;

public class InvalidDeadlineCommandException extends DukeException {

    public InvalidDeadlineCommandException() {
    }

    @Override
    public String getMessage() {
        return " ☹ OOPS!!! invalid usage of Deadline command.\nFormat: deadline <description> /by <yyyy-MM-dd>";
    }
}

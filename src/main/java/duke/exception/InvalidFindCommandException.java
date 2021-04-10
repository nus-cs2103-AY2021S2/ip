package duke.exception;

public class InvalidFindCommandException extends DukeException {
    public InvalidFindCommandException() {
        super();
    }

    @Override
    public String getMessage() {
        return " ☹ OOPS!!! invalid usage of find command.\nFormat: find <keywords>";
    }
}

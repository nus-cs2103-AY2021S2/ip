package duke;

public class InvalidFindCommandException extends DukeException {
    InvalidFindCommandException() {
        super();
    }

    @Override
    public String getMessage() {
        return " ☹ OOPS!!! invalid usage of find command.\nFormat: find <keywords>";
    }
}

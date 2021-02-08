package duke;

public class DukeIncompleteCommandException extends Exception {
    public String message;
    DukeIncompleteCommandException() {
        this.message = "Oh no! Task cannot be empty. ):";
    }
    DukeIncompleteCommandException(String message) {
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }
    public String toString() {
        return message;
    }
}

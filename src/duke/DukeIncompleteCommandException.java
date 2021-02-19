package duke;

public class DukeIncompleteCommandException extends Exception {
    public String message;
    DukeIncompleteCommandException() {
        this.message = "Oh no! Task cannot be empty. ):\n";
    }
    DukeIncompleteCommandException(String message) {
        this.message = message;
    }
    /**
     * Returns an incomplete command error message.
     * @return Error message in the form of a string.
     */
    @Override
    public String getMessage() {
        return message;
    }
    public String toString() {
        return message;
    }
}

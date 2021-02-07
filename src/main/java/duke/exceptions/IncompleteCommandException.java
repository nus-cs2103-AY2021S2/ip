package duke.exceptions;

public class IncompleteCommandException extends DukeException {
    public IncompleteCommandException() {
        super("\nMaster, I'm afraid you are not specific enough.");
    }
}

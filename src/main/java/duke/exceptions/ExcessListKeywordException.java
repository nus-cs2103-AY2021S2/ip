package duke.exceptions;

public class ExcessListKeywordException extends DukeException {
    public ExcessListKeywordException() {
        super("\nMaster, just using the \'list\' command is enough.");
    }
}

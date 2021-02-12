package duke.exceptions;

/**
 * Represents the exception when 'list' command
 * is used along with other unnecessary arguments.
 */
public class ExcessListKeywordException extends DukeException {
    public ExcessListKeywordException() {
        super("ExcessListKeywordException");
    }

    @Override
    public String toString() {
        return "Using just the \'list\' command is enough, Master.";
    }
}

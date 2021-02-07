package duke.exceptions;

/**
 * Represents the exception when 'list' command
 * is used along with other unnecessary arguments.
 */
public class ExcessListKeywordException extends DukeException {
    public ExcessListKeywordException() {
        super("\nMaster, just using the \'list\' command is enough.");
    }

    @Override
    public String toString() {
        return "error";
    }
}

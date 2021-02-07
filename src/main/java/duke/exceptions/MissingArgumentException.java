package duke.exceptions;

/**
 * Represents the exception when using the 'done' command
 * without inputting the task number.
 */

public class MissingArgumentException extends DukeException{
    public MissingArgumentException(String error) {
        super(error);
    }

    @Override
    public String toString() {
        return "\nMaster, I'm afraid you're missing the task number.";
    }
}

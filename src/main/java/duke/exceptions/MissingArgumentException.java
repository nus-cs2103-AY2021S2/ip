package duke.exceptions;

/**
 * Represents the exception when using the 'done' command
 * without inputting the task number.
 */
public class MissingArgumentException extends DukeException{
    public MissingArgumentException() {
        super("MissingArgumentException");
    }

    @Override
    public String toString() {
        return "Master, I'm afraid that you're missing the task number.";
    }
}

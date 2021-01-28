package duke.exceptions;

/**
 * Represents the exception when using the 'done' command
 * without inputting the task number.
 */

public class MissingArgumentException extends Exception{
    public MissingArgumentException(String error) {
        super(error);
    }
}

package duke.exceptions;

/**
 * Represents the exception from inputting a command
 * that is non-existing or not recognised by Alfred.
 */

public class NoKeywordException extends Exception{
    public NoKeywordException(String error) {
        super(error);
    }
}

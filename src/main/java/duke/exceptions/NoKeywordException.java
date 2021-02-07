package duke.exceptions;

/**
 * Represents the exception from inputting a command
 * that is non-existing or not recognised by Alfred.
 */
public class NoKeywordException extends DukeException{
    public NoKeywordException(String error) {
        super("\nSorry Master, I am unable to do that.");
    }

    @Override
    public String toString() {
        return "Error";
    }
}

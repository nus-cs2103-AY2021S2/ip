package duke.exceptions;

/**
 * Represents the exception from input"\nSorry Master but I cannot do that."ting a command
 * that is non-existing or not recognised by Alfred.
 */

public class NoKeywordException extends DukeException{
    public NoKeywordException(String error) {
        super(error);
    }

    @Override
    public String toString() {
        return "\nSorry Master but I am unable do that.";
    }
}

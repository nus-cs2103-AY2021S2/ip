package duke.exceptions;

/**
 * Thrown if Duke command cannot be parsed.
 */
public class DukeExceptionIllegalCommand extends DukeExceptionIllegalArgument {

    public DukeExceptionIllegalCommand(String description) {
        super(description);
    }
}

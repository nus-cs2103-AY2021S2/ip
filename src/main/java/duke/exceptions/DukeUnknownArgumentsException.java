package duke.exceptions;

/**
 * Represents the exception when the input command is unknown.
 */
public class DukeUnknownArgumentsException extends DukeException {

    /**
     * Returns the string representation for the cause of DukeUnknownArgumentsException.
     * @return string representation for the cause of the exception.
     */
    @Override
    public String toString() {
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}

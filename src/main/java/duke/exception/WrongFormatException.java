package duke.exception;

public class WrongFormatException extends DukeException {
    /**
     * Creates a new WrongFormatException.
     */
    public WrongFormatException() {
        super("OOPS!!! The description format is wrong");
    }
}

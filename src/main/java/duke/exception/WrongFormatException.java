package duke.exception;

public class WrongFormatException extends DukeException {
    public WrongFormatException() {
        super("OOPS!!! The description format is wrong");
    }
}

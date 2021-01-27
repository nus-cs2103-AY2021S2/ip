package exception;

import exception.DukeException;

public class WrongFormatException extends DukeException {
    public WrongFormatException() {
        super("OOPS!!! The description format is wrong");
    }
}

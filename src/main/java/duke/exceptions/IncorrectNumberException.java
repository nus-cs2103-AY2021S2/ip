package duke.exceptions;

public class IncorrectNumberException extends DukeException {
    public IncorrectNumberException(int num) {
        super("☹ OOPS!!! The task number "
                + num + " cannot be found." );
    }

}

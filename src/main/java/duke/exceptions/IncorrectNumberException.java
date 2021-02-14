package duke.exceptions;

public class IncorrectNumberException extends DukeException {
    /**
     * Initialises a new IncorrectNumberException object.
     *
     * @param num incorrect number input.
     */
    public IncorrectNumberException(int num) {
        super("☹ OOPS!!! The task number "
                + num + " cannot be found.");
    }

}

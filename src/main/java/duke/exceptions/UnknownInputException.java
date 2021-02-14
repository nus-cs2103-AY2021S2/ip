package duke.exceptions;

public class UnknownInputException extends DukeException {
    /**
     * Initialises a new UnknownInputException object.
     *
     * @param type Empty description of string.
     */
    public UnknownInputException(String type) {
        super("☹ OOPS!!! The description of a "
                + type + " function cannot be empty.");
    }
}

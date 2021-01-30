package duke.exceptions;

/**
 * UnknownInputException class is a class for all the exceptions thrown
 * when Duke faces an input it does not know
 *
 * It inherit from the DukeException class
 */
public class UnknownInputException extends DukeException {
    /**
     * UnknownInputException constructor
     */
    public UnknownInputException () {
        super("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

}

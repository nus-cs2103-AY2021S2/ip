package duke.exceptions;

/**
 * MissingTaskInputException class is a class for all the exceptions thrown when
 * A task related command is inputted but there is incomplete input
 * It inherit from DukeException class
 */
public class MissingTaskInputException extends DukeException {
    /**
     * MissingTaskInput class constructor to initialize an instance
     *
     * @param action the action causing the exception to be thrown
     */
    public MissingTaskInputException(String action) {
        super("     ☹ OOPS!!! The description of a " + action + " cannot be empty.");
    }

}

package duke.exceptions;

/**
 * <code>EmptyDescriptionException</code> handles incorrect user
 * inputs which has no description for the task entered.
 */
public class EmptyDescriptionException extends DukeException {

    /**
     * Constructor for EmptyDescriptionException class.
     *
     * @param task Specific type of task.
     */
    public EmptyDescriptionException(String task) {
        super ("☹ OOPS!!! The description for the following type of task cannot be empty. ☹\n "
                + "Please try again!\n"
                + "      Task Type: " + task);
    }
}

package Exceptions;

public class EmptyDescriptionException extends DukeException {

    public EmptyDescriptionException(String task) {
        super("     ☹ OOPS!!! The description for the following type of task cannot be empty.\n "
                + "     Please try again!\n"
                + "     Task Type: " + task);
    }
}

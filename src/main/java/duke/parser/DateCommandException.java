package duke.parser;

import duke.commands.DateCommand;

/**
 * DateCommandException is raised when the user gives a date related command in an invalid format.
 */
public class DateCommandException extends Exception {
    /**
     * Constructs a date command exception.
     *
     * @param e A String containing the error message.
     */
    public DateCommandException(String e) {
        super(e);
    }
}

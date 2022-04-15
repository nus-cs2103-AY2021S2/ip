package duke.parser;

/**
 * WrongArgumentsException is raised when the user gives invalid commands
 */

public class WrongArgumentException extends Exception {
    /**
     * Constructs a wrong argument exception.
     *
     * @param e A String containing the error message.
     */
    public WrongArgumentException(String e) {
        super(e);
    }
}

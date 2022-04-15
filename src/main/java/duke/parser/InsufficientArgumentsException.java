package duke.parser;

/**
 * InsufficientArgumentsException is raised when the user gives less command than required
 */
public class InsufficientArgumentsException extends Exception {

    /**
     * Constructs an insufficient arguments exception.
     *
     * @param e A String containing the error message.
     */
    public InsufficientArgumentsException(String e) {
        super(e);
    }
}

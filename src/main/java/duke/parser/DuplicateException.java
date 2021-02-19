package duke.parser;

/**
 * DuplicateException is raised when the user adds the same task more than one time
 */
public class DuplicateException extends Exception {

    /**
     * Constructs a duplicate exception.
     *
     * @param e A String containing the error message.
     */
    public DuplicateException(String e) {
        super(e);
    }
}

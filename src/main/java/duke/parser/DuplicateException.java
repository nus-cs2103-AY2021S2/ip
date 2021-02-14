package duke.parser;

/**
 * DuplicateException is raised when the user adds the same task more than one time
 */
public class DuplicateException extends Exception {
    public DuplicateException(String e) {
        super(e);
    }
}

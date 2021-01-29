package duke.exceptions;

/**
 * <code>InvalidFolderException</code> handles the case where
 * there is no folder for the task file to be created in.
 */
public class InvalidFolderException extends DukeException {

    /**
     * Constructor for InvalidFolderException class.
     */
    public InvalidFolderException() {
        super("     ☹ OOPS!!! Error creating the file.\n"
                + "     Please create a data folder before trying again!");
    }
}

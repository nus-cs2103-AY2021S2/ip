package duke.exceptions;

/**
 * <code>InvalidFileDataException</code> handles the case where
 * there is an invalid data in the file to be read.
 */
public class InvalidFileDataException extends DukeException {

    /**
     * Constructor for InvalidFileDataException class.
     */
    public InvalidFileDataException() {
        super ("☹ OOPS!!! Error reading the file. ☹");
    }
}

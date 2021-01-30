package duke.exceptions;

/**
 * DukeOnlyIOException class is a class for all the IO exceptions thrown during Duke
 * It inherit from the DukeException class
 */
public class DukeOnlyIoException extends DukeException {
    /**
     * DukeOnlyIOException constructor used to initialize the Exception
     *
     */
    public DukeOnlyIoException() {
        super("A IO Exception occurred!");
    }

}

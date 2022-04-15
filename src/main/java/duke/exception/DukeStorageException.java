package duke.exception;

/**
 * Class representing Storage exceptions in Duke, sub-class of DukeException.
 */
public class DukeStorageException extends DukeException {
    /**
     * Constructor of DukeStorageException.
     *
     * @param errorMsg Error message prompting user of error when saving/retrieving tasks
     *                 from storage.
     */
    public DukeStorageException(String errorMsg) {
        super(errorMsg);
    }
}

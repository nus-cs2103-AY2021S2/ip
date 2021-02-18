package bearbear.exceptions;

/**
 * Signals failure in writing or reading to data file.
 */
public class StorageException extends DukeException {
    public StorageException(String errorMessage) {
        super(errorMessage);
    }
}

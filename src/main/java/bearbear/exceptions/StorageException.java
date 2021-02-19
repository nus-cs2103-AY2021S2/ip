package bearbear.exceptions;

/**
 * Signals failure in writing or reading to data file.
 */
public class StorageException extends BearBearException {
    public StorageException(String errorMessage) {
        super(errorMessage);
    }
}

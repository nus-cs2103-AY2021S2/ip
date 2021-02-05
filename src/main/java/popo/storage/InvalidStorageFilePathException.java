package popo.storage;

/**
 * Signals an invalid file path for storage.
 */
public class InvalidStorageFilePathException extends Exception {
    public InvalidStorageFilePathException(String errorMessage) {
        super(errorMessage);
    }
}

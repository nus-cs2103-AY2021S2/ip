package duke.storage;

public class InvalidStorageFilePathException extends Exception {
    public InvalidStorageFilePathException(String errorMessage) {
        super(errorMessage);
    }
}

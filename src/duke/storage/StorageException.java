package duke.storage;

public class StorageException extends Exception {
    public StorageException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}

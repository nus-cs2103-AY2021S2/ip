package duke.storage;

/**
 * Represents a failure to load or store something.
 *
 * @author Benedict Khoo
 */
public class StorageException extends Exception {
    public StorageException(String message) {
        super(message);
    }

    /**
     * Returns the exception message.
     *
     * @return The exception message.
     */
    @Override
    public String toString() {
        return getMessage();
    }
}

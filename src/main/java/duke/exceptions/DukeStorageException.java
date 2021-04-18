package duke.exceptions;

/**
 * An exception thrown when Duke encounters an error storing or loading files in the Storage class.
 */
public class DukeStorageException extends Exception {
    public DukeStorageException(String message) {
        super(message);
    }
}

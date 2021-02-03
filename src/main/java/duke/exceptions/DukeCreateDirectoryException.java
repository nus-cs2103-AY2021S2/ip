package duke.exceptions;

/**
 * Represents the exception when a directory cannot be created.
 */
public class DukeCreateDirectoryException extends DukeException {
    private final String dir;

    /**
     * Constructs a DukeCreateDirectoryException containing the string representation of the
     * data directory.
     * @param dir String representation of the data directory
     */
    public DukeCreateDirectoryException(String dir) {
        this.dir = dir;
    }

    /**
     * Returns string representation for the cause of the DukeCreateDirectoryException.
     * @return cause of the DukeCreateDirectoryException.
     */
    @Override
    public String toString() {
        return String.format("Failed to create new directory: '%s'.", dir);
    }
}

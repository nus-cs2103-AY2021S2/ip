package duke.exceptions;

/**
 * Represents the exception when the save file does not conform to the format of the proper save
 * file.
 */
public class DukeCorruptedStorageException extends DukeException {

    /**
     * Returns string representation of the exception.
     * @return cause of DukeCorruptedStorageException.
     */
    @Override
    public String toString() {
        return "Storage file is corrupted. Continue to reset save file.";
    }
}

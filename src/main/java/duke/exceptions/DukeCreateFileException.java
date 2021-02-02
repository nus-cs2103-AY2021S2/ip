package duke.exceptions;

/**
 * Represents the exception when the save file cannot be created.
 */
public class DukeCreateFileException extends DukeException {

    /**
     * Returns string representation for the cause of DukeCreateFileException.
     * @return string representation for the cause of the exception.
     */
    @Override
    public String toString() {
        return "Failed to create new file: save.txt.";
    }
}

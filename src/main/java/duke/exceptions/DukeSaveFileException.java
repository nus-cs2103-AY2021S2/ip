package duke.exceptions;

/**
 * Represents the exception when save file cannot be updated.
 */
public class DukeSaveFileException extends DukeException {

    /**
     * Returns a string representation for the cause of DukeSaveFileException.
     * @return string representation for the cause of the exception.
     */
    @Override
    public String toString() {
        return "Failed to update save file.";
    }
}

package duke.exceptions;

/**
 * Error class for invalid content format in save files.
 */
public class InvalidSaveFileFormatException extends DukeException {
    /**
     * Constructs a InvalidSaveFileFormatException with specific message
     * for invalid content format in save files.
     */
    public InvalidSaveFileFormatException() {
        super("\tInvalid content format in save file.\t");
    }
}

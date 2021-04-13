package duke;

/**
 * Exception where Duke was unable to save file.
 */
public class DukeSaveFileException extends DukeException {

    /**
     * Returns string message of save file exception.
     *
     * @return String message of save file exception.
     */
    @Override
    public String toString() {
        return "Sorry the file could not be saved in drive!\n";
    }
}

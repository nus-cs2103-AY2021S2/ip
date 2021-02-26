package duke.exceptions;

public class SaveFileInvalidFormatException extends DukeException {
    public SaveFileInvalidFormatException() {
        super("Invalid content format in save file.");
    }
}

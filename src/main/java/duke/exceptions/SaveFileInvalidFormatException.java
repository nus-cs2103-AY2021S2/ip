package duke.exceptions;

public class SaveFileInvalidFormatException extends DukeException {
    public SaveFileInvalidFormatException() {
        super("\tInvalid content format in save file.\t");
    }
}

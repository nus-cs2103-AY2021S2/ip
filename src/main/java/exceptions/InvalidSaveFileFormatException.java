package exceptions;

public class InvalidSaveFileFormatException extends DukeException {
    public InvalidSaveFileFormatException() {
        super("\tInvalid content format in save file.\t");
    }
}

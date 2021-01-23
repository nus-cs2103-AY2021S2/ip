//public class InvalidSaveFileFormatException extends Exception {
public class InvalidSaveFileFormatException extends DukeException {
    public InvalidSaveFileFormatException() {
        super("\tInvalid content format in save file.\t");
    }
}

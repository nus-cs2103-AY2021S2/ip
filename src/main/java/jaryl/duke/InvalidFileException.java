package jaryl.duke;

public class InvalidFileException extends DukeException {
    public InvalidFileException() {
        super("Error while attempting to read file from disk");
    }
}

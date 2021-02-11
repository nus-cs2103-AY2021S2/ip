package duke.exceptions;

/**
 * Represents the exceptions related to data storage in the file.
 */
public class FileIoException extends ChatBotException {
    public FileIoException() {
        super("OH NO!!! There is a File Io Error!!");
    }
}

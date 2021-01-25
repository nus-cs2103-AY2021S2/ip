package duke.exceptions;

public class DukeCorruptedStorageException extends DukeException {

    @Override
    public String toString() {
        return "Storage file is corrupted.";
    }
}

package duke.exceptions;

public class DukeCorruptedStorageException extends DukeException {
    @Override
    public String toString() {
        return "duke.storage.Storage file is corrupted.";
    }
}

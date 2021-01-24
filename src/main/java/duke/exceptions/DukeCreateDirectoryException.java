package duke.exceptions;

public class DukeCreateDirectoryException extends DukeException {
    String dir;

    public DukeCreateDirectoryException(String dir) {
        this.dir = dir;
    }

    @Override
    public String toString() {
        return String.format("Failed to create new directory: '%s'.", dir);
    }
}

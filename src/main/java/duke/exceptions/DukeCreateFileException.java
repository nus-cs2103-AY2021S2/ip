package duke.exceptions;

public class DukeCreateFileException extends DukeException {

    @Override
    public String toString() {
        return "Failed to create new file: save.txt.";
    }
}

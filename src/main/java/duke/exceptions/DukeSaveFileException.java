package duke.exceptions;

public class DukeSaveFileException extends DukeException {

    @Override
    public String toString() {
        return "Failed to update save file.";
    }
}

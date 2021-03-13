package duke.exception;

public class DukeCorruptFileException extends DukeException {
    /**
     *  DukeCorruptFileException constructor.
     */
    public DukeCorruptFileException() {
    }

    @Override
    public String getMessage() {
        return "Corrupt File";
    }

    @Override
    public String toString() {
        return "    Corrupt File";
    }
}

package duke.exception;

public class DukeIoException extends DukeException {
    /**
     *  DukeIOException constructor.
     */
    public DukeIoException() {
    }

    @Override
    public String getMessage() {
        return "File IO Error";
    }

    @Override
    public String toString() {
        return "    File IO Error";
    }
}

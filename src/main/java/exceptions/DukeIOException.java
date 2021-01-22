package exceptions;

public class DukeIOException extends DukeException {
    /**
     *  DukeIOException constructor.
     */
    public DukeIOException() {
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

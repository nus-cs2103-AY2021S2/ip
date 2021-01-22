package duke.exception;

public class DukeIndexRangeException extends DukeException {

    /**
     *  DukeIndexOutOfRangeException constructor.
     */
    public DukeIndexRangeException() {
    }

    @Override
    public String getMessage() {
        return "Index provided out of range";
    }

    @Override
    public String toString() {
        return "    Index provided out of range";
    }
}

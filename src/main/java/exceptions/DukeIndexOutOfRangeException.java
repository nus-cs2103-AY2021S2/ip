package exceptions;

public class DukeIndexOutOfRangeException extends DukeException {

    /**
     *  DukeIndexOutOfRangeException constructor.
     */
    public DukeIndexOutOfRangeException() {
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

package duke.exception;

public class DukeTaskAlreadyDoneException extends DukeException {
    /**
     *  DukeIndexOutOfRangeException constructor.
     */
    public DukeTaskAlreadyDoneException() {
    }

    @Override
    public String getMessage() {
        return "Task is already done.";
    }

    @Override
    public String toString() {
        return "    Task is already done.";
    }
}

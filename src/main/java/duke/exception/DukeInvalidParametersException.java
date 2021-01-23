package duke.exception;

public class DukeInvalidParametersException extends DukeException {
    /**
     * InvalidParametersException.
     * Occurs when invalid parameters provided given valid command.
     *
     * @author Yap Jing Kang
     */
    public DukeInvalidParametersException() {
    }

    @Override
    public String getMessage() {
        return "Invalid Parameters given";
    }

    @Override
    public String toString() {
        return "    Invalid Parameters given";
    }

}

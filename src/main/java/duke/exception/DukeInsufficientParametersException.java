package duke.exception;

/**
 *  InsufficientParametersException.
 *  Occurs when insufficient parameters provided given valid command.
 *
 *  @author Yap Jing Kang
 */
public class DukeInsufficientParametersException extends DukeException {
    protected String command;

    /**
     *  DukeInsufficientParametersException constructor.
     *
     *  @param cmd Name of command in question.
     */
    public DukeInsufficientParametersException(String cmd) {
        this.command = cmd;
    }

    @Override
    public String getMessage() {
        return String.format("Description of %s cannot be empty", command);
    }

    @Override
    public String toString() {
        return String.format("    Description of %s cannot be empty", command);
    }
}

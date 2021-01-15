package exceptions;

/**
 *  InsufficientParametersException.
 *  Occurs when insufficient parameters provided given valid command.
 *
 *  @author Yap Jing Kang
 */
public class DukeInsufficientParametersException extends DukeException {
    protected String command;

    /**
     *  InsufficientParametersException constructor.
     *
     *  @param cmd Name of command in question.
     */
    public DukeInsufficientParametersException(String cmd) {
        this.command = cmd;
    }

    @Override
    public String toString() {
        return String.format("    Description of %s cannot be empty", command);
    }
}

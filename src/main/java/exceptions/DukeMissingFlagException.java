package exceptions;

/**
 *  MissingFlagException.
 *  Occurs when mandatory flags are not given.
 *
 *  @author Yap Jing Kang
 */
public class DukeMissingFlagException extends DukeException {
    protected String command;
    protected String flag;

    /**
     *  MissingFlagException constructor.
     *
     *  @param cmd Name of command in question.
     *  @param flag Missing flag.
     */
    public DukeMissingFlagException(String cmd, String flag) {
        this.command = cmd;
        this.flag = flag;
    }

    @Override
    public String toString() {
        return String.format("    %s expects '%s' flag; None found", command, flag);
    }
}

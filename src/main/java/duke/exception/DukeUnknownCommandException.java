package duke.exception;

/**
 *  UnknownCommandException.
 *  Occurs when unrecognised command is provided.
 *
 *  @author Yap Jing Kang
 */
public class DukeUnknownCommandException extends DukeException {

    /**
     *  DukeUnknownCommandException constructor.
     */
    public DukeUnknownCommandException() {}

    @Override
    public String getMessage() {
        return "I do not know what you are trying to do here..";
    }

    @Override
    public String toString() {
        return "    I do not know what you are trying to do here..";
    }
}

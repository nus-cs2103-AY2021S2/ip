package exceptions;

/**
 *  UnknownCommandException.
 *  Occurs when unrecognised command is provided.
 *
 *  @author Yap Jing Kang
 */
public class UnknownCommandException extends DukeException {

    /**
     *  InsufficientParametersException constructor.
     */
    public UnknownCommandException() {}

    @Override
    public String toString() {
        return "I do not know what you are trying to do here..";
    }
}

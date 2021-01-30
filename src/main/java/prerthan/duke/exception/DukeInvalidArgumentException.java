package prerthan.duke.exception;

/**
 * InvalidArgumentException
 */
public class DukeInvalidArgumentException extends DukeException {
    private static final long serialVersionUID = 5221851007504557801L;

    public DukeInvalidArgumentException(String message, String thrownBy) {
        super(message, thrownBy);
    }
}
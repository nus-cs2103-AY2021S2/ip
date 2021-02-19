package bearbear.exceptions;

/**
 * Signals an invalid user argument.
 */
public class InvalidArgumentException extends BearBearException {
    public InvalidArgumentException(String errorMessage) {
        super(errorMessage);
    }
}

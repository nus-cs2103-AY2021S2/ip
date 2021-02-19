package bearbear.exceptions;

/**
 * Signals failure in initialising user interface of application.
 */
public class UiException extends BearBearException {
    public UiException(String errorMessage) {
        super(errorMessage);
    }
}

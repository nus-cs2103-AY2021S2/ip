package ekud.common.exception;

/**
 * Exception to be thrown when user does not provide sufficient number of arguments.
 */
public class IncompleteDetailException extends EkudException {

    public IncompleteDetailException(String missing) {
        super(missing + Messages.MISSING_DETAIL_PREFIX);
    }
}

package ekud.common.exception;

public class IncompleteDetailException extends EkudException {
    public IncompleteDetailException(String missing) {
        super(missing + " is missing");
    }
}

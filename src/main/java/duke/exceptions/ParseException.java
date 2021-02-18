package duke.exceptions;

/**
 * Extends RuntimeException and is used when parsing fails.
 */
public class ParseException extends RuntimeException {

    private final String errMsg;

    /**
     * Returns a ParseException exception with given error message.
     *
     * @param errMsg error message
     */
    public ParseException(String errMsg) {
        super(errMsg);
        this.errMsg = errMsg;
    }

    public String getErrMsg() {
        return errMsg;
    }
}

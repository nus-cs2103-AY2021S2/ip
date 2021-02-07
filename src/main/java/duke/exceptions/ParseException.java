package duke.exceptions;

/**
 * Extends RuntimeException and is used when parsing fails.
 */
public class ParseException extends RuntimeException {

    private final String msgDes;

    /**
     * Builds the exception with given message.
     *
     * @param message the information of the error message.
     */
    public ParseException(String message) {
        super(message);
        msgDes = message;
    }

    public String getMsgDes() {
        return msgDes;
    }
}
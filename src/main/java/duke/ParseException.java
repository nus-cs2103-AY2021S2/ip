package duke;

/**
 * Extends RuntimeException and is used when parsing fails.
 */
public class ParseException extends RuntimeException {

    private final String msgDes;

    public ParseException(String message) {
        super(message);
        msgDes = message;
    }

    public String getMsgDes() {
        return msgDes;
    }
}

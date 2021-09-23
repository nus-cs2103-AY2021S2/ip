package dbot.exception;

/**
 * DBotException encapsulates all the expected exceptions to be encountered whilst running DBot.
 */
public class DBotException extends Exception {

    public DBotException(String message) {
        super(message);
    }

    public DBotException(Throwable cause) {
        super(cause);
    }

    public DBotException(String message, Throwable cause) {
        super(message, cause);
    }
}

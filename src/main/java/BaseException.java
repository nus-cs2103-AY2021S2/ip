/**
 * The type Base exception.
 */
public abstract class BaseException extends Exception {

    /**
     * The Message.
     */
    public String message;

    /**
     * Instantiates a new Base exception.
     *
     * @param message the message
     */
    public BaseException(String message) {
        this.message = message;
    }
    /**
     * Returns error message.
     *
     * @return  message the error message
     */
    public String getMessage() {
        return message;
    }
}

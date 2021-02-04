package ekud.common.exception;

/**
 * Exceptions meant for Ekud.
 */
public class EkudException extends Exception {
    protected String message;

    /**
     * Construct an exception for Ekud.
     *
     * @param message The exception message.
     */
    public EkudException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}

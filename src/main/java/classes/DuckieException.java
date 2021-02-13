package classes;

/**
 * DuckieException inherits from Exception class.
 */
public class DuckieException extends Exception {
    /**
     * Constructor method.
     * @param message Error message.
     */
    public DuckieException(String message) {
        super(message);
    }

    /**
     * Overriding method to obtain custom error message.
     * @return specified error message.
     */
    @Override
    public String toString() {
        return super.getMessage();
    }
}

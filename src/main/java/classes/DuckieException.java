package main.java.classes;

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
}

package surrealchat.exception;

/**
 * Custom Exception class for SurrealChat. Can be used for any anomalous inputs.
 */
public class SurrealException extends Exception {

    /**
     * Constructs SurrealException object.
     * @param message The message for Meme Man display.
     */
    public SurrealException(String message) {
        super(message);
    }
}

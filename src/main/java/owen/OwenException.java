package owen;

/**
 * Exception thrown by Owen chatbot.
 */
public class OwenException extends Exception {
    /**
     * Creates new OwenException with a message.
     *
     * @param message Message to attach to this exception.
     */
    public OwenException(String message) {
        super("Ooooo noo...\n" + message);
    }
}

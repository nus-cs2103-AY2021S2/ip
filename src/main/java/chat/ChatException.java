package chat;

/**
 * Exception class specifically for errors from Chat the Cat.
 */

public class ChatException extends Exception{

    /**
     * Initialises ChatException object.
     * 
     * @param message Error message.
     */
    public ChatException(String message) {
        super(message);
    }

}

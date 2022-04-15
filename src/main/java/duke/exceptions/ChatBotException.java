package duke.exceptions;

/**
 * Represents the exceptions that the chat bot Duke might encounter.
 */
public class ChatBotException extends Exception {
    /**
     * Default constructor for the DateFormatException class.
     * @param message error that the bot encountered.
     */
    public ChatBotException(String message) {
        super(message);
    }
}

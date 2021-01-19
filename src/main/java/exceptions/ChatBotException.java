package exceptions;
import java.lang.Exception;

public class ChatBotException extends Exception {
    public ChatBotException(String message) {
        super(message);
    }
}

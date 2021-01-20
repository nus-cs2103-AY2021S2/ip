import java.util.ArrayList;
import java.util.Arrays;

public class ChatException extends Exception{

    private static String errorCat = "(=^> <^=)'''";

    public ChatException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return errorCat + "\n" + super.toString();
    }

}

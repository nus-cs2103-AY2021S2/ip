package rick.exceptions;

public class InvalidEventCommandException extends RickException {
    @Override
    public String getMessage() {
        return "Invalid event command format!\n"
                + "Valid format: event <description> /on <yyyy-mm-dd>";
    }
}
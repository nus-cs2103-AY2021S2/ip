package rick.exceptions;

public class InvalidTodoCommandException extends RickException {
    @Override
    public String getMessage() {
        return "Invalid todo command format!\n"
                + "Valid format: todo <description>";
    }
}
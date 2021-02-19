package rick.exceptions;

public class InvalidDoneCommandException extends RickException {
    @Override
    public String getMessage() {
        return "Invalid done command format!\n"
                + "Valid format: done <task index>";
    }
}
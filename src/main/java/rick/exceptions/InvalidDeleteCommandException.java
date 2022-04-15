package rick.exceptions;

public class InvalidDeleteCommandException extends RickException {
    @Override
    public String getMessage() {
        return "Invalid delete command format!\n"
                + "Valid format: delete <task index>";
    }
}
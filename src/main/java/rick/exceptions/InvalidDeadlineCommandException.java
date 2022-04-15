package rick.exceptions;

public class InvalidDeadlineCommandException extends RickException {
    @Override
    public String getMessage() {
        return "Invalid deadline command format!\n"
                + "Valid format: deadline <description> /by <yyyy-mm-dd>";
    }
}

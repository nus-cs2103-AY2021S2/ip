package rick.exceptions;

public class InvalidFindCommandException extends RickException {
    @Override
    public String getMessage() {
        return "Invalid find command format!\n"
                + "Valid format: find <keyword/keyphrase>";
    }
}
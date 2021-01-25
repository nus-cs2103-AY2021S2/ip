package mike;

public class ParseException extends RuntimeException {
    private final String description;

    public ParseException(String message) {
        super(message);
        this.description = message;
    }

}

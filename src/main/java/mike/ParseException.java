package mike;

/**
 * Extends RunTimeException and is thrown when parsing fails
 */
public class ParseException extends RuntimeException {
    private final String description;

    public ParseException(String message) {
        super(message);
        this.description = message;
    }

}

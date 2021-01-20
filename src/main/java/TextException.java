public class TextException extends RuntimeException {
    private final String description;

    public TextException(String message) {
        super(message);
        this.description = message;
    }

}

public class InvalidInputException extends IllegalArgumentException {
    public InvalidInputException() {
        super("    Your input format doesn't seem to be valid for this command! Please try it again :c");
    }
}

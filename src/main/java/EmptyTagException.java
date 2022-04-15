public class EmptyTagException extends IllegalArgumentException {
    public EmptyTagException() {
        super("☹ Please provide a label for the tag.");
    }
}

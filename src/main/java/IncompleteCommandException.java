public class IncompleteCommandException extends Exception {
    IncompleteCommandException() {
        super("The description cannot be empty.");
    }
}

package exception;

public class EmptyDescriptionException extends Exception {
    public EmptyDescriptionException(String msg) {
        super("OOPS!!! The description of a(n) " + msg + " cannot be empty.");
    }
}

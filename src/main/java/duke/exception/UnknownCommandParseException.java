package duke.exception;

@SuppressWarnings("serial")
public class UnknownCommandParseException extends Exception {
    public UnknownCommandParseException() {
        super("\tOops! Sorry, I do not know what that means.");
    }
}

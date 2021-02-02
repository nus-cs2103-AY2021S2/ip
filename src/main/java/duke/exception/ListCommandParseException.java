package duke.exception;

@SuppressWarnings("serial")
public class ListCommandParseException extends Exception {
    public ListCommandParseException() {
        super("\tPlease follow this format \"list\".");
    }
}

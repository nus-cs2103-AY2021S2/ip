package duke.exception;

@SuppressWarnings("serial")
public class ExitCommandParseException extends Exception {
    public ExitCommandParseException() {
        super("\tPlease follow this format \"bye\".");
    }
}

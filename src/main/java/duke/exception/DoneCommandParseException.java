package duke.exception;

@SuppressWarnings("serial")
public class DoneCommandParseException extends Exception {
    public DoneCommandParseException() {
        super("\tPlease follow this format \"done <index>\".");
    }
}

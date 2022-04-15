package duke.exception;

@SuppressWarnings("serial")
public class FindCommandParseException extends Exception {
    public FindCommandParseException() {
        super("\tPlease follow this format \"find <keyword>\".");
    }
}

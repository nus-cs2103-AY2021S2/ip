package duke.exception;

@SuppressWarnings("serial")
public class AddDeadlineCommandParseException extends Exception {
    public AddDeadlineCommandParseException() {
        super("\tPlease follow this format \"deadline <todo> /by <datetime>\".");
    }
}

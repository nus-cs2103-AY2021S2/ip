package duke.exception;

@SuppressWarnings("serial")
public class AddEventCommandParseException extends Exception {
    public AddEventCommandParseException() {
        super("\tPlease follow this format \"event <todo> /at <datetime>\".");
    }
}

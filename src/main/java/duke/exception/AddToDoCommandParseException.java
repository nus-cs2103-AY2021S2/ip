package duke.exception;

@SuppressWarnings("serial")
public class AddToDoCommandParseException extends Exception {
    public AddToDoCommandParseException() {
        super("\tPlease follow this format \"todo <task>\".");
    }
}

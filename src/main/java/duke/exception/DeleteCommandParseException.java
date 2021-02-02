package duke.exception;

@SuppressWarnings("serial")
public class DeleteCommandParseException extends Exception {
    public DeleteCommandParseException() {
        super("\tPlease follow this format \"delete <index>\".");
    }
}

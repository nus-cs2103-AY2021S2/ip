package duke.commands;

public class InvalidDescriptionException extends Exception {

    public InvalidDescriptionException(String errorMessage) {
        super(errorMessage);
    }
}

package duke.exceptions;

public class MissingDescriptionException extends Exception {

    public MissingDescriptionException(String action) {
        super("Your action '" + action + "' needs to have a description!!!");
    }
}

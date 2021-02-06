package duke.exceptions;

public class MissingDescriptionException extends Exception {

    public MissingDescriptionException(String action) {
        super("You need to add a description for the action '" + action + "' :-)");
    }
}

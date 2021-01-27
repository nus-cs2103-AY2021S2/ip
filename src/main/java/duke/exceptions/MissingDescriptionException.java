package duke.exceptions;

public class MissingDescriptionException extends ChatBotException {
    public MissingDescriptionException(String type) {
        super("OOPS!!! The description of a " + type + " cannot be empty.");
    }
}

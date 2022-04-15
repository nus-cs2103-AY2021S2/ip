package duke.exceptions;

/**
 * Represents the exceptions when the task name is no specified.
 */
public class MissingDescriptionException extends ChatBotException {
    public MissingDescriptionException(String type) {
        super("OOPS!!! The description of a " + type + " cannot be empty.");
    }
}

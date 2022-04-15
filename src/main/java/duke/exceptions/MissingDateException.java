package duke.exceptions;

/**
 * Represents the exceptions when the date is no specified.
 */
public class MissingDateException extends ChatBotException {
    public MissingDateException() {
        super("OOPS!!! The date is missing!");
    }
}

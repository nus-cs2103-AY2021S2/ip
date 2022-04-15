package duke.exceptions;

/**
 * Represents the exceptions when the date is in the wrong format.
 */
public class InvalidDateFormatException extends ChatBotException {
    public InvalidDateFormatException() {
        super("Please key in the date in the format of: dd mm yyyy");
    }
}

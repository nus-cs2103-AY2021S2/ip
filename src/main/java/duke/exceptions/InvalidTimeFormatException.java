package duke.exceptions;

/**
 * Represents the exceptions when the time is in the wrong format.
 */
public class InvalidTimeFormatException extends ChatBotException {
    public InvalidTimeFormatException() {
        super("Please key in the time in the format of: eg. 12 12 2021 03:00 pm - 05:00 pm");
    }
}

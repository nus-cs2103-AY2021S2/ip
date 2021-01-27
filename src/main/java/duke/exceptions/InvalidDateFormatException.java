package duke.exceptions;

public class InvalidDateFormatException extends ChatBotException {
    public InvalidDateFormatException() {
        super("Please key in the date in the format of: dd mm yyyy");
    }
}

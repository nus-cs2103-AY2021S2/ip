package chatbot.exceptions;

public class InvalidTimeFormatException extends ChatBotException {
    public InvalidTimeFormatException() {
        super("Please key in the time in the format of: eg. 12 12 2021 03:00 PM to 05:00 PM");
    }
}

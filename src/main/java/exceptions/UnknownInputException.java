package exceptions;

public class UnknownInputException extends ChatBotException {
    public UnknownInputException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}

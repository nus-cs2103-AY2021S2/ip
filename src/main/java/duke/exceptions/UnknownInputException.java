package duke.exceptions;

/**
 * Represents the exceptions when the input is invalid.
 */
public class UnknownInputException extends ChatBotException {
    public UnknownInputException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}

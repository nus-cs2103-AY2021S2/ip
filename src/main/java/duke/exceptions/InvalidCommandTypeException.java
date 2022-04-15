package duke.exceptions;

/**
 * Represents the exceptions when the command line is invalid.
 */
public class InvalidCommandTypeException extends ChatBotException {
    public InvalidCommandTypeException() {
        super("There is no such command word");
    }
}

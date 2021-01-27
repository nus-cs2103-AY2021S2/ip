package duke.exceptions;

public class InvalidCommandTypeException extends ChatBotException {
    public InvalidCommandTypeException() {
        super("There is no such command word");
    }
}

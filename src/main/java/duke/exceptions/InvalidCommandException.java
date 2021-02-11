package duke.exceptions;

/**
 * Represents the exception when an invalid command keyword is given
 */
public class InvalidCommandException extends DukeException{
    public InvalidCommandException() {
        super("InvalidCommandException");
    }

    @Override
    public String toString() {
        return "Master, I require you to give me a valid command.";
    }
}

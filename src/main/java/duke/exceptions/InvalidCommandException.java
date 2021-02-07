package duke.exceptions;

public class InvalidCommandException extends DukeException{
    public InvalidCommandException() {
        super("Master, I require you to give me a valid command.");
    }

}

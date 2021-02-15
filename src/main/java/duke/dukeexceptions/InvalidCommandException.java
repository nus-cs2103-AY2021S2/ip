package duke.dukeexceptions;

/**
 * Models the exception where an invalid command is not sent by the user.
 */
public class InvalidCommandException extends DukeException {
    public InvalidCommandException() {
        super("That is not a valid command format! Send 'help' if you need assistance!");
    }
}

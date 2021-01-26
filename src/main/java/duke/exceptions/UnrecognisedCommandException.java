package duke.exceptions;

public class UnrecognisedCommandException extends DukeException {
    public UnrecognisedCommandException(String s) {
        super("You have entered \"" + s + "\", which is an unrecognised command. Please try again!");
    }
}

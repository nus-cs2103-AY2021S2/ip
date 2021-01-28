package soonkeatneo.duke;

public class InvalidCommandException extends IllegalArgumentException {
    public InvalidCommandException() {
        super("    This isn't a valid command!");
    }
}

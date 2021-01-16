package Exceptions;

public class UnknownCommandException extends DukeException{
    public UnknownCommandException (String command) {
        super("Sorry I do not understand the command \"" + command + "\" :(");
    }
}

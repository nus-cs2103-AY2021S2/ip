package yoda.command;

public class InvalidCommandFormatException extends Exception {
    public InvalidCommandFormatException(String error) {
        super(error);
    }
}

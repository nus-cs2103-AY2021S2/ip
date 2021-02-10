package yoda.command;

public class InvalidDateTimeFormatException extends Exception {
    public InvalidDateTimeFormatException(String error) {
        super(error);
    }
}

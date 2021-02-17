package duke.exceptions;

public class InvalidDateTimeFormatException extends Exception {

    public InvalidDateTimeFormatException(String dateString) {
        super("'" + dateString + "' is not a date grrrh... Can you follow 'YYYY-MM-DD HH:mm'?");
    }
}

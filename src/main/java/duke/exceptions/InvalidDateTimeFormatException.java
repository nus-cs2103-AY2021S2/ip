package duke.exceptions;

public class InvalidDateTimeFormatException extends Exception {

    public InvalidDateTimeFormatException(String dateString) {
        super("I can't recognize '" + dateString + "' as a date. Please follow the 'YYYY-MM-DD HH:mm' format :P");
    }
}

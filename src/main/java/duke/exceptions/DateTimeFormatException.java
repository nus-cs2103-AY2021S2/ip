package duke.exceptions;

public class DateTimeFormatException extends Exception {

    public DateTimeFormatException(String dateString) {
        super("I can't recognize '" + dateString + "' as a date. Please follow the 'YYYY-MM-DD HH:mm' format :P");
    }
}

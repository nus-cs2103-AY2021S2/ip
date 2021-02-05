package CustomExceptions;

public class DateTimeFormatNotRecognizedException extends Exception {

    public DateTimeFormatNotRecognizedException(String dateString) {
        super("I can't recognize '" + dateString + "' as a date. Please follow the 'YYYY-MM-DD HH:mm' format :P");
    }
}

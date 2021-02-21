package yoda.command;

/**
 * InvalidDateTimeFormatException class that throws an exception when the datetime entered by user is invalid.
 */
public class InvalidDateTimeFormatException extends Exception {
    /**
     * Creates an InvalidDateTimeFormatException exception.
     * @param error The error the user made when entering their command.
     */
    public InvalidDateTimeFormatException(String error) {
        super(error);
    }
}

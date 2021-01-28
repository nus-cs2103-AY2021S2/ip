package duke.exceptions;

/**
 * Represents the exception from the getDateTime() function
 * in Event or Deadline objects when the wrong format is
 * being inputted.
 */

public class DateTimeFormatException extends Exception {
    public DateTimeFormatException(String error) {
        super(error);
    }
}
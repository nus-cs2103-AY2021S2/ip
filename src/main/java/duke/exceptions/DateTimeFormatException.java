package duke.exceptions;

/**
 * Represents the exception from the getDateTime() function
 * in Event or Deadline objects when the wrong format is
 * being inputted.
 */

public class DateTimeFormatException extends DukeException {
    public DateTimeFormatException() {
        super("\nMaster, please input the date and time as such: \"YYYY-MM-DD HH:MM\"");
    }
}
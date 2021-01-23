package exceptions;

public class DateTimeNotFoundException extends DukeException {
    public DateTimeNotFoundException() {
        super("\tPlease enter the date (DD/MM/YYYY) with optional\n"
                + "\ttime (in 24 hours format) after \"/by\" for Deadline Tasks\n"
                + "\tor date with optional start and end time after \"/at\"\n"
                + "\tfor Event Tasks.\n");
    }
}

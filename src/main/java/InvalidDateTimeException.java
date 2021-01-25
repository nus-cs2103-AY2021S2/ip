public class InvalidDateTimeException extends DukeException {
    public InvalidDateTimeException() {
        super("Please enter your date in one of the following formats:\n"
                + "d/M/yyyy HHmm OR d MMM yy HHmm OR dd-MM-yy HHmm");
    }
}

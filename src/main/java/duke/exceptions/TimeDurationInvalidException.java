package duke.exceptions;

public class TimeDurationInvalidException extends DukeException {
    public TimeDurationInvalidException() {
        super("\tPlease enter a valid start and end time duration\n\t(start time < end time).\n");
    }
}

//public class InvalidTimeDurationException extends Exception {
public class InvalidTimeDurationException extends DukeException {
    public InvalidTimeDurationException() {
        super("\tPlease enter a valid start and end time duration\n\t(start time < end time).\n");
    }
}

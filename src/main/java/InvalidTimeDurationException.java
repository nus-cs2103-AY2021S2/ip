public class InvalidTimeDurationException extends Exception {
    public InvalidTimeDurationException() {
        super("Please enter a valid start and end time duration (start time < end time).");
    }
}

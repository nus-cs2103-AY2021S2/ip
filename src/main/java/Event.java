public class Event extends Task {
    private String eventTime;

    public Event(String[] input) {
        super(input[0]);
        eventTime = input[1].substring(3);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), eventTime);
    }
}

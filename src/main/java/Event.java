public class Event extends Task {
    private final String eventTime;

    public Event(String[] input) {
        super(input[0]);
        eventTime = input[1].substring(3);
    }

    @Override
    public String data() {
        return String.format("E | %s | %s", super.data(), eventTime);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), eventTime);
    }
}

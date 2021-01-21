public class EventTask extends Task {
    private final String event;

    public EventTask(final String desc, final String date) {
        super(desc);
        this.event = date;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), event);
    }
}

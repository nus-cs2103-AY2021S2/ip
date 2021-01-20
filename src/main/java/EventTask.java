public class EventTask extends Task {
    private final String event;
    public EventTask(String desc, String date) {
        super(desc);
        this.event = date;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), event);
    }
}

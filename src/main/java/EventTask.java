public class EventTask extends Task {
    private static final String IDENTIFIER = "E";

    private String eventTime;

    public EventTask(String name, String eventTime) {
        super(IDENTIFIER, name);
        this.eventTime = eventTime;
    }

    public EventTask(String name, boolean isCompleted, String eventTime) {
        super(IDENTIFIER, name, isCompleted);
        this.eventTime = eventTime;
    }

    public String getEventTime() {
        return eventTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventTime + ")";
    }
}

public class EventTask extends Task {
    private String eventTime;

    public EventTask(String name, String eventTime) {
        super(name);
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventTime + ")";
    }
}

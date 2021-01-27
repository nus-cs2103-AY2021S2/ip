public class EventTask extends Task {
    private String eventTime;

    public EventTask(String name) {
        super(name);
    }

    public EventTask(String name, String eventTime) {
        super(name);
        this.eventTime = eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventTime() {
        return eventTime;
    }

    @Override
    public String toSavedString() {
        return String.format(
                "E | %d | %s | %s",
                this.getIsDone() ? 1 : 0,
                this.getName(),
                this.getEventTime()
        );
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + (this.eventTime != null ? String.format(" (at: %s)", this.eventTime) : "");
    }
}

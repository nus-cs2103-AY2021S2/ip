package duke.tasks;

public class Event extends Task {
    private final String eventTime;

    public Event(String description, String eventTime) {
        super(description, "[E]");
        this.eventTime = eventTime;
    }

    public String getEventTime() {
        return eventTime;
    }

    @Override
    public String toString() {
        String str = eventTime.strip();
        return super.toString() + "(at: " + str + ")";
    }
}

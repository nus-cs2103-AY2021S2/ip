package duke.tasks;

/**
 * Represents a task with an event time.
 */
public class Event extends Task {
    protected final String eventTime;

    public Event(String description, String eventTime) {
        super(description, "[E]");
        this.eventTime = eventTime;
    }

    /**
     * Returns time of the event.
     * @return event time
     */
    public String getEventTime() {
        return eventTime;
    }

    @Override
    public String toString() {
        String str = eventTime.strip();
        return super.toString() + "(at: " + str + ")";
    }
}

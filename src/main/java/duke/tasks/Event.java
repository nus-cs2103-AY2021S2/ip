package duke.tasks;

/**
 * Represents a task with an event time.
 */
public class Event extends Task {
    private final String eventTime;

    /**
     * Creates a {@code Event} object with a task description and a task event time component.
     * @param description Task description.
     * @param eventTime Event time of task.
     */
    public Event(String description, String eventTime) {
        super(description, "[E]");
        this.eventTime = eventTime;
    }

    /**
     * Returns time of the event.
     * @return Event time.
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

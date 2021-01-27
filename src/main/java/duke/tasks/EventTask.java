package duke.tasks;

/**
 * Represents a task with an event time.
 */
public class EventTask extends Task {
    public static final String IDENTIFIER = "E";

    private String eventTime;

    public EventTask(String name, String eventTime) {
        super(IDENTIFIER, name);
        this.eventTime = eventTime;
    }

    public EventTask(String name, boolean isCompleted, String eventTime) {
        super(IDENTIFIER, name, isCompleted);
        this.eventTime = eventTime;
    }

    /**
     * Returns the event time.
     *
     * @return event time
     */
    public String getEventTime() {
        return eventTime;
    }

    @Override
    public String getTaskType() {
        return IDENTIFIER;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventTime + ")";
    }
}

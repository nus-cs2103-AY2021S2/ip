package kuga.tasks;

/**
 * Represents a task with an event time.
 */
public class EventTask extends Task {
    public static final String IDENTIFIER = "E";

    private final String eventTime;

    /**
     * Creates an {@code EventTask} object with the given task name and event time,
     * with the task set to initially not completed.
     *
     * @param name      Name of the task.
     * @param eventTime String denoting the event time.
     */
    public EventTask(String name, String eventTime) {
        super(IDENTIFIER, name);
        this.eventTime = eventTime;
    }

    /**
     * Creates an {@code EventTask} object with the given task name and event time,
     * with the task set to the boolean isCompleted.
     *
     * @param name        Name of the task.
     * @param isCompleted Boolean indicating whether the task has been completed.
     * @param eventTime   String denoting the event time.
     */
    public EventTask(String name, boolean isCompleted, String eventTime) {
        super(IDENTIFIER, name, isCompleted);
        this.eventTime = eventTime;
    }

    /**
     * Returns the event time.
     *
     * @return Event time.
     */
    public String getEventTime() {
        return eventTime;
    }

    @Override
    public String getTaskType() {
        return taskType;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventTime + ")";
    }
}

package duke.tasks;

/**
 * Represents a task with an event time.
 */
public class EventTask extends Task {
    public static final String IDENTIFIER = "E";

    private String eventTime;

    /**
     * Creates an {@code EventTask} object with the given task name and event time,
     * with the task set to initially not completed.
     *
     * @param name      name of the task
     * @param eventTime string denoting the event time
     */
    public EventTask(String name, String eventTime) {
        super(IDENTIFIER, name);
        this.eventTime = eventTime;
    }

    /**
     * Creates an {@code EventTask} object with the given task name and event time,
     * with the task set to the boolean isCompleted.
     *
     * @param name        name of the task
     * @param isCompleted boolean indicating whether the task has been completed
     * @param eventTime   string denoting the event time
     */
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
        return taskType;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventTime + ")";
    }
}

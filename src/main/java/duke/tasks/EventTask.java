package duke.tasks;

/**
 * Represents a task with an event time.
 */
public class EventTask extends Task {
    public static final String IDENTIFIER = "E";

    private final String EVENT_TIME;

    /**
     * Creates an {@code EventTask} object with the given task name and event time,
     * with the task set to initially not completed.
     *
     * @param name      Name of the task.
     * @param eventTime String denoting the event time.
     */
    public EventTask(String name, String eventTime) {
        super(IDENTIFIER, name);
        EVENT_TIME = eventTime;
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
        EVENT_TIME = eventTime;
    }

    /**
     * Returns the event time.
     *
     * @return Event time.
     */
    public String getEventTime() {
        return EVENT_TIME;
    }

    @Override
    public String getTaskType() {
        return TASK_TYPE;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + EVENT_TIME + ")";
    }
}

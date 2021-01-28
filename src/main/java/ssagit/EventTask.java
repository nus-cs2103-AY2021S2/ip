package ssagit;

/**
 * Represent a task for events
 */
public class EventTask extends Task {
    String time;

    public EventTask(String taskName, boolean isDone, String time) {
        super(taskName, isDone);
        this.time = time;
    }

    @Override
    /**
     * Returns a formatted string of the state of the task
     */
    public String toString() {
        if (isDone) return "event | done | " + taskName + " | " + time;
        return "event | not done | " + taskName + " | " + time;
    }

    /**
     * Used when adding tasks
     * @return More human readable toString()
     */
    public String toFormattedString() {
        if (isDone) return "[E][X] " + taskName + " (at: " + time + ")";
        return "[E][ ] " + taskName + " (at: " + time + ")";
    }
}
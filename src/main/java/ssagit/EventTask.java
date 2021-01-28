package ssagit;
import java.util.Date;

/**
 * Represent a task for events
 */
public class EventTask extends Task {
    Date deadline;

    public EventTask(String taskName, boolean isDone, Date deadline) {
        super(taskName, isDone);
        this.deadline = deadline;
    }

    @Override
    /**
     * Returns a formatted string of the state of the task
     */
    public String toString() {
        if (isDone) return "event | done | " + taskName + " | " + deadline.toString();
        return "event | not done | " + taskName + " | " + deadline.toString();
    }

    /**
     * Used when adding tasks
     * @return More human readable toString()
     */
    public String toFormattedString() {
        if (isDone) return "[E][X] " + taskName + " (at: " + time + ")";
        return "[E][ ] " + taskName + " (at: " + deadline.toString() + ")";
    }
}
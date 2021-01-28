package ssagit;
import java.util.Date;

/**
 * Represent a task for deadlines
 */
public class DeadlineTask extends Task{
    Date deadline;

    public DeadlineTask(String taskName, boolean isDone, Date deadline) {
        super(taskName, isDone);
        this.deadline = deadline;
    }

    @Override
    /**
     * Returns a formatted string of the state of the task
     */
    public String toString() {
        if (isDone) return "deadline | done | " + taskName + " | " + deadline.toString();
        return "deadline | not done | " + taskName + " | " + deadline.toString();
    }

    /**
     * Used when adding tasks
     * @return More human readable toString()
     */
    public String toFormattedString() {
        if (isDone) return "[D][X] " + taskName + " (by: " + time + ")";
        return "[D][ ] " + taskName + " (by: " + deadline.toString() + ")";
    }
}

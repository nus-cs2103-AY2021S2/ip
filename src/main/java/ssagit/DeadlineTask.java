package ssagit;

/**
 * Represent a task for deadlines
 */
public class DeadlineTask extends Task{
    String time;

    public DeadlineTask(String taskName, boolean isDone, String time) {
        super(taskName, isDone);
        this.time = time;
    }

    @Override
    /**
     * Returns a formatted string of the state of the task
     */
    public String toString() {
        if (isDone) return "deadline | done | " + taskName + " | " + time;
        return "deadline | not done | " + taskName + " | " + time;
    }

    /**
     * Used when adding tasks
     * @return More human readable toString()
     */
    public String toFormattedString() {
        if (isDone) return "[D][X] " + taskName + " (by: " + time + ")";
        return "[D][ ] " + taskName + " (by: " + time + ")";
    }
}

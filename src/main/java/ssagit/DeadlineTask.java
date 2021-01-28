package ssagit;
import java.util.Date;

/**
 * Represent a task for deadlines
 */
public class DeadlineTask extends Task{
    String inputDate;
    Date deadline;

    public DeadlineTask(String taskName, boolean isDone, String inputDate, Date deadline) {
        super(taskName, isDone);
        this.inputDate = inputDate;
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
        if (isDone) return "[D][X] " + taskName + " (by: " + deadline.toString() + ")";
        return "[D][ ] " + taskName + " (by: " + deadline.toString() + ")";
    }

    /**
     * Used when outputting to file, date format will be parsable when file is fed in again
     * @return String format viable for use by FileWriter
     */
    public String toOutputFileString() {
        if (isDone) return "deadline | done | " + taskName + " | " + inputDate;
        return "deadline | not done | " + taskName + " | " + inputDate;
    }
}

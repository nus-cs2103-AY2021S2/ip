package duke.taskclass;
import java.util.Date;

/**
 * Represent a task for events
 */
public class EventTask extends Task {
    String inputDate;
    Date deadline;

    public EventTask(String taskName, boolean isDone, String inputDate, Date deadline) {
        super(taskName, isDone);
        this.inputDate = inputDate;
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
        if (isDone) return "[E][X] " + taskName + " (at: " + deadline.toString() + ")";
        return "[E][ ] " + taskName + " (at: " + deadline.toString() + ")";
    }

    /**
     * Used when outputting to file, date format will be parsable when file is fed in again
     * @return String format viable for use by FileWriter
     */
    public String toOutputFileString() {
        if (isDone) return "event | done | " + taskName + " | " + inputDate;
        return "event | not done | " + taskName + " | " + inputDate;
    }
}
package duke.taskclass;
import java.util.Date;

/**
 * Represent a task for events
 */
public class EventTask extends Task {
    String inputDate;
    Date deadline;

    /**
     * Constructor for EventTask.
     * @param taskName Name of task.
     * @param isDone Is task complete?
     * @param inputDate The date of the event in String format.
     * @param deadline The date of the event in Date format.
     */
    public EventTask(String taskName, boolean isDone, String inputDate, Date deadline) {
        super(taskName, isDone);
        this.inputDate = inputDate;
        this.deadline = deadline;
    }


    /**
     * Returns a formatted string of the state of the task
     */
    @Override
    public String toString() {
        if (isDone) {
            return "event | done | " + taskName + " | " + deadline.toString();
        }
        return "event | not done | " + taskName + " | " + deadline.toString();
    }

    /**
     * Used when adding tasks
     * @return More human readable toString()
     */
    public String toFormattedString() {
        if (isDone) {
            return "[E][X] " + taskName + " (at: " + deadline.toString() + ")";
        }
        return "[E][ ] " + taskName + " (at: " + deadline.toString() + ")";
    }

    /**
     * Used when outputting to file, date format will be parsable when file is fed in again
     * @return String format viable for use by FileWriter
     */
    public String toOutputFileString() {
        if (isDone) {
            return "event | done | " + taskName + " | " + inputDate;
        }
        return "event | not done | " + taskName + " | " + inputDate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other) {
        final EventTask task = (EventTask) other;
        if (task == this) {
            return true;
        }
        if (this.taskName.equals(task.taskName)) {
            if (this.deadline.compareTo(task.deadline) == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.taskName != null ? this.taskName.hashCode() : 0);
        hash = 53 * hash + (this.deadline != null ? this.deadline.hashCode() : 0);
        return hash;
    }
}

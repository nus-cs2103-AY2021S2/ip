package duke.taskclass;

import java.util.Date;

/**
 * Represent a task for deadlines
 */
public class DeadlineTask extends Task {
    private String inputDate;
    private Date deadline;

    /**
     * Constructor for DeadlineTask.
     *
     * @param taskName  Name of task.
     * @param isDone    Is task complete?
     * @param inputDate The deadline of the Task in String format.
     * @param deadline  The deadline of the Task in Date format.
     */
    public DeadlineTask(String taskName, boolean isDone, String inputDate, Date deadline) {
        super(taskName, isDone);
        this.inputDate = inputDate;
        this.deadline = deadline;
    }

    /**
     * Returns a formatted string of the state of the task
     */
    @Override
    public String toString() {
        if (this.getIsDone()) {
            return "deadline | done | " + this.getTaskName() + " | " + deadline.toString();
        }
        return "deadline | not done | " + this.getTaskName() + " | " + deadline.toString();
    }

    /**
     * Used when adding tasks
     *
     * @return More human readable toString()
     */
    public String toFormattedString() {
        if (this.getIsDone()) {
            return "[D][X] " + this.getTaskName() + " (by: " + deadline.toString() + ")";
        }
        return "[D][ ] " + this.getTaskName() + " (by: " + deadline.toString() + ")";
    }

    /**
     * Used when outputting to file, date format will be parsable when file is fed in again
     *
     * @return String format viable for use by FileWriter
     */
    public String toOutputFileString() {
        if (this.getIsDone()) {
            return "deadline | done | " + this.getTaskName() + " | " + inputDate;
        }
        return "deadline | not done | " + this.getTaskName() + " | " + inputDate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other) {
        final DeadlineTask task = (DeadlineTask) other;
        if (task == this) {
            return true;
        }
        if (this.getTaskName().equals(task.getTaskName())) {
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
        hash = 53 * hash + (this.getTaskName() != null ? this.getTaskName().hashCode() : 0);
        hash = 53 * hash + (this.deadline != null ? this.deadline.hashCode() : 0);
        return hash;
    }
}

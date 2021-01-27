package main.java;
import java.time.LocalDate;

/**
 * This class represents a deadline task.
 */
class Deadline extends Task {
    protected final LocalDate date;

    /**
     * Creates a new deadline task object.
     *
     * @param taskName The name of the deadline
     * @param deadline The date in which the deadline is due
     */
    public Deadline(String taskName, LocalDate deadline) {
        super(taskName);
        this.date = deadline;
    }

    /**
     * Overloaded constructor to create a deadline task object. It accepts one extra argument
     * to determine if the task is already done.
     *
     * @param taskName The name of the deadline
     * @param isDone Whether the deadline is already done
     * @param deadline The date in which the deadline is due
     */
    public Deadline(String taskName, boolean isDone, LocalDate deadline) {
        super(taskName, isDone);
        this.date = deadline;
    }

    /**
     * Getter method to get the date in which the deadline is due.
     *
     * @return the date in which the deadline is due
     */
    public String getDate() {
        return Task.printDate(this.date);
    }

    /**
     * Prints the details of the deadline in a special format.
     *
     * @return the details of the deadline, such as the type, whether it is done and its due date.
     */
    @Override
    public String toString() {
        if (this.isDone) {
            return "[D][X] " + this.taskName + " (by: " + Task.printDate(date) + ")";
        } else {
            return "[D][ ] " + this.taskName + " (by: " + Task.printDate(date) + ")";
        }
    }
}
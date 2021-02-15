package duke;
import java.time.LocalDate;

/**
 * This class represents a deadline task.
 */
class Deadline extends Task {
    private final LocalDate dateOfDeadline;

    /**
     * Creates a new deadline task object.
     *
     * @param taskName The name of the deadline task.
     * @param dateOfDeadline The date on which the deadline task is due.
     */
    public Deadline(String taskName, LocalDate dateOfDeadline) {
        super(taskName);
        this.dateOfDeadline = dateOfDeadline;
    }

    /**
     * Overloaded constructor to create a deadline task object. It accepts one extra
     * argument to determine if the task is already completed.
     *
     * @param taskName The name of the deadline task.
     * @param isDone Whether the deadline task is already completed.
     * @param dateOfDeadline The date on which the deadline task is due.
     */
    public Deadline(String taskName, boolean isDone, LocalDate dateOfDeadline) {
        super(taskName, isDone);
        this.dateOfDeadline = dateOfDeadline;
    }

    /**
     * Getter method to get the date on which the deadline is due.
     *
     * @return The date in which the deadline is due.
     */
    public String getDate() {
        return Task.printDate(this.dateOfDeadline);
    }

    /**
     * Prints the details of the deadline task in a special format.
     *
     * @return A string representation of the details of the deadline task.
     */
    @Override
    public String toString() {
        assert (this.taskName.equals("")) : "Name of deadline cannot be empty";
        String typeOfTask = "[D]";
        String isCompleted;
        if (this.isDone) {
            isCompleted = "[X]";
        } else {
            isCompleted = "[ ]";
        }
        String message = typeOfTask + isCompleted + " " + this.taskName;
        message += " (by: " + Task.printDate(dateOfDeadline) + ")";
        return message;
    }
}

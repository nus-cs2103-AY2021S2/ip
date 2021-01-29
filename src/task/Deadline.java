package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class for tasks with a deadline.
 */
public class Deadline extends Task{
    LocalDate deadLine;

    /**
     * Constructs an undone Deadline task with a deadline and a name.
     *
     * @param taskName Name of the Deadline task.
     * @param deadLine Deadline of the Deadline task.
     */
    public Deadline(String taskName, LocalDate deadLine) {
        super(taskName);
        this.deadLine = deadLine;
    }

    /**
     * Constructs a Deadline task with a deadline, a name and status.
     *
     * @param taskName Name of the Deadline task.
     * @param done Status of the task, done or not done.
     * @param deadLine Deadline of the Deadline task.
     */
    public Deadline(String taskName, boolean done, LocalDate deadLine) {
        super(taskName, done);
        this.deadLine = deadLine;
    }

    /**
     * Returns a string representation of the Deadline task.
     *
     * @return A string representation of the Deadline task.
     */
    public String toString() {
        String formattedDate = this.deadLine.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }

    /**
     * Parse the task to comply with CSV format.
     *
     * @return A string that complies with CSV format.
     */
    public String parseToCSVRow() {
        return "D," + super.isDone() + "," + super.getTaskName() + "," + this.deadLine;
    }

    /**
     * Returns the deadline of the Task.
     *
     * @return Deadline of the Task.
     */
    public LocalDate getTaskTime(){
        return deadLine;
    }
}

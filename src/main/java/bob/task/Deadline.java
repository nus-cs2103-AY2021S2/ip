package bob.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task
 */
public class Deadline extends Task {
    private final LocalDate deadline;
    private final LocalTime time;

    /**
     * Constructor of a deadline
     * @param name Name of the deadline
     * @param deadline The deadline of the task
     * @param time The due time of the task
     */
    public Deadline(String name, LocalDate deadline, LocalTime time) {
        super(name);
        this.deadline = deadline;
        this.time = time;
    }

    /**
     * Constructor of a deadline
     * @param name Name of the deadline
     * @param isDone Status of the task
     * @param deadline The deadline of the task
     * @param time The due time of the task
     */
    public Deadline(String name, boolean isDone, LocalDate deadline, LocalTime time) {
        super(name, isDone);
        this.deadline = deadline;
        this.time = time;
    }

    public LocalDate getDeadline() {
        return this.deadline;
    }

    public LocalTime getTime() {
        return this.time;
    }

    @Override
    public LocalDateTime getDateTime() {
        return LocalDateTime.of(deadline, time);
    }

    @Override
    public String getType() {
        return "D";
    }

    /**
     * Prints out the details of the Deadline, including the name, the due date, and due time.
     * @return A string representing the deadline
     */
    @Override
    public String toString() {
        String head = "[D][ ] ";
        if (this.isDone) {
            head = "[D][X] ";
        }
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a");
        return head + this.name + " (by: " + this.deadline.format(dateFormatter)
                + " " + this.time.format(timeFormatter) + ")";
    }
}

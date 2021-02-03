package duke;

import java.time.LocalDate;

/**
 * Represents a deadline task
 */
public class Deadline extends Task {
    private LocalDate deadline;
    private String time;

    /**
     * Constructor of a deadline
     * @param name Name of the deadline
     * @param deadline The deadline of the task
     * @param time The due time of the task
     */
    public Deadline(String name, LocalDate deadline, String time) {
        super(name);
        this.deadline = deadline;
        this.time = time;
    }

    /**
     * Constructor of a deadline
     * @param name Name of the deadline
     * @param done Status of the task
     * @param deadline The deadline of the task
     * @param time The due time of the task
     */
    public Deadline(String name, boolean done, LocalDate deadline, String time) {
        super(name, done);
        this.deadline = deadline;
        this.time = time;
    }

    public LocalDate getDeadline() {
        return this.deadline;
    }

    public String getTime() {
        return this.time;
    }

    /**
     * Prints out the details of the Deadline, including the name, the due date, and due time.
     * @return A string representing the deadline
     */
    @Override
    public String toString() {
        String head = "[D][ ] ";
        if (this.done) {
            head = "[D][X] ";
        }
        return head + this.name + " (by: " + this.deadline.getMonth() + " "
                + this.deadline.getDayOfMonth() + " " + this.deadline.getYear() + " "
                + this.time + ")";
    }
}

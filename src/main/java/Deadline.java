import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    private String name;
    private LocalDate deadline;
    private boolean isDone;

    /**
     * Constructor for Deadline which has not been done.
     * @param name Name of task.
     * @param deadline Deadline of task in String.
     */
    Deadline(String name, String deadline) {
        this.name = name;
        this.deadline = LocalDate.parse(deadline);
        this.isDone = false;
    }

    /**
     * Constructor for Deadline.
     * @param name Name of task
     * @param deadline Deadline of task in String.
     * @param done Indicates if task has been done.
     */
    Deadline(String name, String deadline, boolean done) {
        this.name = name;
        this.deadline = LocalDate.parse(deadline);
        this.isDone = done;
    }

    /**
     * Constructor for Deadline.
     * @param name Name of task.
     * @param deadline Deadline of task in LocalDate.
     * @param done Indicates if task has been done.
     */
    Deadline(String name, LocalDate deadline, boolean done) {
        this.name = name;
        this.deadline = deadline;
        this.isDone = done;
    }

    /**
     * Marks task as done.
     * @return Completed task.
     */
    @Override
    Task done() {
        return new Deadline(this.name, this.deadline.toString(), true);
    }

    /**
     * Sets the deadline to a new deadline.
     * @param date New deadline.
     * @return Deadline with new deadline.
     */
    @Override
    Task set(String date) {
        return new Deadline(this.name, date, this.isDone);
    }

    /**
     * Checks the equivalence of Deadline this and Object obj.
     * If obj is an instance of the Deadline class and all attributes are equivalent,
     * it is equivalent to this.
     * @param obj the object which will be compared to this.
     * @return Indication of whether obj is equivalent to this.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof Deadline) {
            Deadline deadline = (Deadline) obj;
            return deadline.name.equals(this.name)
                    && (deadline.deadline.equals(this.deadline))
                            && (deadline.isDone == this.isDone);
        }
        return false;
    }

    /**
     * Represents a Deadline instance in String.
     * @return String representation of Deadline.
     */
    @Override
    public String toString() {
        if (this.isDone) {
            return String.format("[D][X] %s (by: %s)",
                                 this.name,
                                 this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
        }
        return String.format("[D][ ] %s (by: %s)",
                             this.name,
                             this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}

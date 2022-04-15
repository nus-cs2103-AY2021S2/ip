import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class that extends from Task parent class.
 * Represents a deadline task.
 */
public class Deadline extends Task {

    private LocalDate deadline;

    /**
     * Constructs a deadline object.
     *
     * @param description task description.
     * @param deadline LocalDate object that describes the deadline date.
     */
    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Constructs a deadline object.
     *
     * @param description task description.
     * @param deadline LocalDate object that describes the deadline.
     * @param isDone task done status.
     */
    public Deadline(String description, LocalDate deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    /**
     * Gets the task deadline.
     *
     * @return string of task deadline.
     */
    public LocalDate getDeadline() {
        return this.deadline;
    }

    /**
     * Sets the deadline of task to new deadline.
     *
     * @param deadline new deadline to be changed to.
     * @return the new deadline.
     */
    public LocalDate setDeadline(LocalDate deadline) {
        this.deadline = deadline;
        return this.deadline;
    }

    /**
     * Overrides Task's toString method.
     *
     * @return String output for the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }
}

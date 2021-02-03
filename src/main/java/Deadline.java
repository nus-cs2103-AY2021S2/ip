import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class that extends from Task parent class.
 * Represents a deadline task.
 */
public class Deadline extends Task {

    private LocalDate deadline;

    /**
     * Constructor method.
     *
     * @param description task description.
     * @param deadline LocalDate object that describes the deadline.
     */
    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Overloaded constructor method.
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
     * Getter method for task deadline.
     *
     * @return string of task deadline
     */
    public LocalDate getDeadline() {
        return this.deadline;
    }

    /**
     * Method that allows user to change the deadline of task
     *
     * @param deadline new deadline to be changed to
     * @return the new deadline
     */
    public LocalDate changeDeadline(LocalDate deadline) {
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

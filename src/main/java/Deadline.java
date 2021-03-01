import java.util.Date;

/**
 * The Deadline class is a child class of the Task Class,
 * it specifies the task as a Deadline using [D]
 */
public class Deadline extends Task {
    protected Date deadlineBy;

    public Deadline(String description, Date deadlineBy) {
        super(description);
        this.deadlineBy = deadlineBy;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + deadlineBy + ")";
    }
}

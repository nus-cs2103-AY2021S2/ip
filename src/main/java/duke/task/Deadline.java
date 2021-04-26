package duke.task;

import java.time.LocalDate;

/**
 * One kind of Task that need to be done before the date.
 */
public class Deadline extends Task {

    private final LocalDate deadline;

    /** Creates a deadline with description and date of deadline.
     *
     * @param description description of the deadline.
     * @param deadline date of the deadline.
     */
    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.deadline + ")";
    }
}

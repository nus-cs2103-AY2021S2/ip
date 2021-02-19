package rick;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A child of <code>Task</code> object, corresponds to a deadline task with
 * description supplied by the user. eg., <code>buy cake /by 2019-10-19</code>
 *
 * @see Task
 */
public class Deadline extends Task {
    private LocalDate date;

    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    public Deadline(String description, LocalDate date, boolean isDone) {
        super(description, isDone);
        this.date = date;
    }

    @Override
    public String toString() {
        return "D | " + super.toString() + " | " + date.format(DateTimeFormatter.ofPattern("dd MMM YYYY"));
    }
}

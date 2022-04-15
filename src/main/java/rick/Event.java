package rick;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A child of <code>Task</code> object, corresponds to an event task with
 * description supplied by the user. eg., <code>alan's birthday /at 6 Aug 6-8pm</code>
 *
 * @see Task
 */
public class Event extends Task {
    private LocalDate date;

    public Event(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    public Event(String description, LocalDate date, boolean isDone) {
        super(description, isDone);
        this.date = date;
    }

    @Override
    public String toString() {
        return "E | " + super.toString() + " | " + date.format(DateTimeFormatter.ofPattern("dd MMM YYYY"));
    }
}

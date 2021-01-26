package duke.tasks;

import java.time.LocalDateTime;

/**
 * DateTask class.
 *
 * Extension of Task class to include a datetime field and
 * the corresponding datetime getter. Field set during construction.
 */
public class DateTask extends Task {

    protected LocalDateTime datetime;

    /**
     * Constructor for DateTask.
     *
     * @param description Description of DateTask.
     * @param isDone Whether task is completed.
     */
    public DateTask(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns datetime field of DateTask.
     *
     * @return Datetime.
     */
    public LocalDateTime getDatetime() {
        return datetime;
    }
}

package duke.tasks;

import java.time.LocalDateTime;

import duke.exceptions.DukeExceptionIllegalArgument;
import duke.parser.DatetimeParser;


/**
 * DateTask class.
 *
 * Extension of Task class to include a datetime field and
 * the corresponding datetime getter. Field set during construction.
 */
public abstract class DateTask extends Task {

    private LocalDateTime datetime;

    /**
     * Constructor for DateTask.
     *
     * @param description Description of DateTask.
     * @param isDone Whether task is completed.
     */
    protected DateTask(String description, boolean isDone) {
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

    /**
     * Sets datetime field.
     */
    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    /**
     * Parses datetime for DateTask from string.
     *
     * @param datetime string representation of datetime
     * @return LocalDateTime
     * @throws DukeExceptionIllegalArgument When datetime parsing fails
     */
    protected static LocalDateTime parseDatetime(String datetime) throws DukeExceptionIllegalArgument {
        return DatetimeParser.parseDate(datetime);
    }

    /**
     * Returns string representation of datetime in ISO format
     *
     * @return string representation of datetime
     */
    public String getDatetimeString() {
        return DatetimeParser.formatDateIso(datetime);
    }
}

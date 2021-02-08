package duke.tasks;

import duke.exceptions.DukeExceptionIllegalArgument;
import duke.parser.DatetimeParser;

import java.time.LocalDate;
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

    /**
     * Sets datetime field.
     */
    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    /**
     * Parses datetime for DateTask.
     *
     * @param datetime string representation of datetime
     * @return LocalDateTime
     * @throws DukeExceptionIllegalArgument When datetime parsing fails
     */
    protected static LocalDateTime parseDatetime(String datetime) throws DukeExceptionIllegalArgument {
        return DatetimeParser.parseDate(datetime);
    }

    public String getDatetimeString() {
        return DatetimeParser.formatDateISO(datetime);
    }
}

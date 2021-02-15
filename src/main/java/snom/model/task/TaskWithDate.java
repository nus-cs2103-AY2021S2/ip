package snom.model.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import snom.common.core.Messages;
import snom.common.exceptions.SnomException;
import snom.common.util.TaskUtil;

/**
 * Stores extra date information for {@code Task}
 */
public class TaskWithDate extends Task {
    private LocalDateTime dateTime;

    /**
     * Constructs a {@code TaskWithDate}
     *
     * @param description    description of task
     * @param dateTime       date time of task
     * @throws SnomException if invalid date time is given
     */
    public TaskWithDate(String description, String dateTime) throws SnomException {
        super(description);
        this.dateTime = convertDateTime(dateTime);
    }

    /**
     * Returns a formatted date time.
     * Eg. Tue 26 Jan 2021 03:33pm
     *
     * @return formatted date time
     */
    public String getDateTimeString() {
        return this.dateTime.format(TaskUtil.DATE_TIME_OUTPUT_FORMAT);
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Returns a LocalDateTime by converting from a given string.
     *
     * @param dateTime          string representing date and time
     * @return                  converted/formatted LocalDateTime
     * @throws SnomException    if the given string is an invalid date time format
     */
    public LocalDateTime convertDateTime(String dateTime) throws SnomException {
        dateTime = dateTime.replaceFirst(" ", "");
        try {
            LocalDateTime formattedDateTime = LocalDateTime.parse(dateTime, TaskUtil.DATE_TIME_INPUT_FORMAT);
            return formattedDateTime;
        } catch (DateTimeParseException e) {
            throw new SnomException(Messages.ERROR_INVALID_DATE_TIME);
        }
    }

    @Override
    public String getSaveString() {
        return super.getSaveString() + ", " + this.dateTime.format(TaskUtil.DATE_TIME_SAVE_FORMAT);
    }
}

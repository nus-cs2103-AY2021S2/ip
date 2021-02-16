package duke.tasks;

import static duke.common.Utils.DATETIME_FORMAT;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * An abstract class for timed task to provide ease in accessing datetime from the respective tasks.
 */
public abstract class TimedTask extends Task {

    protected final LocalDateTime taskDateTime;

    /**
     * Constructor for Timed Task, specifying the description and datetime.
     *
     * @param description description of the timed task
     * @param taskDateTime datetime of the timed task
     */
    public TimedTask(String description, String taskDateTime) {
        super(description);
        this.taskDateTime = LocalDateTime.parse(taskDateTime, DATETIME_FORMAT);
    }

    /**
     * Constructor for Timed Task, specifying the status, description and datetime.
     *
     * @param description description of the timed task
     * @param taskDateTime datetime of the timed task
     */
    public TimedTask(int done, String description, String taskDateTime) {
        super(done, description);
        this.taskDateTime = LocalDateTime.parse(taskDateTime, DATETIME_FORMAT);
    }

    public LocalDateTime getTaskDateTime() {
        return taskDateTime;
    }

    public LocalDate getTaskDate() {
        return taskDateTime.toLocalDate();
    }
}

package ekud.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

/**
 * Base class for any tasks that has relevant date and time information attached
 */
public abstract class TaskWithDateTime extends Task {
    protected static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy h.mma");
    protected LocalDateTime dateTime;

    /**
     * Construct a new Task that has date and time information attached.
     *
     * @param description The TaskWithDateTime description.
     * @param dateTime    The datetime information.
     */
    public TaskWithDateTime(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    /**
     * Get the datetime information for the Task.
     *
     * @return The stored datetime.
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * @return String LinkedList containing {isDone, description, dateTime}
     */
    @Override
    public LinkedList<String> export() {
        LinkedList<String> list = super.export();
        list.addLast(dateTime.toString());
        return list;
    }
}

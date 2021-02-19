package duke.model.task;

import static duke.commons.util.AppUtil.formatDate;
import static duke.commons.util.AppUtil.formatStorageDate;

/**
 * Represents a Event {@code TimedTask}.
 */
public class Event extends TimedTask {
    private static final String STRING_FORMAT = "[E]%s (at: %s)";
    private static final String STORAGE_STRING_FORMAT = "E | %d | %s | %s";

    /**
     * Constructor for Event {@code TimedTask}, specifying the description and occurrence date.
     *
     * @param description description of the event
     * @param eventDateTime occurrence date of the event
     */
    public Event(String description, String eventDateTime) {
        super(description, eventDateTime);
    }

    /**
     * Constructor for Event {@code TimedTask}, specifying the task's status, description and occurrence date.
     *
     * @param done integer value to indicate the event's status
     * @param description description of the event
     * @param eventDateTime occurrence date of the event
     */
    public Event(int done, String description, String eventDateTime) {
        super(done, description, eventDateTime);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj instanceof Event) {
            TimedTask timedTask = (TimedTask) obj;
            return timedTask.description.equalsIgnoreCase(super.description)
                    && timedTask.taskDateTime.equals(super.taskDateTime);
        }

        return false;
    }

    @Override
    public String toString() {
        return String.format(STRING_FORMAT, super.toString(), formatDate(super.taskDateTime));
    }

    @Override
    public String toStorageString() {
        return String.format(STORAGE_STRING_FORMAT, isDone ? 1 : 0, description, formatStorageDate(super.taskDateTime));
    }
}

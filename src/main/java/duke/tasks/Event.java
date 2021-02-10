package duke.tasks;

import duke.common.Utils;

/**
 * Represents a Event {@code Task}.
 */
public class Event extends TimedTask {
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
            return timedTask.description.equalsIgnoreCase(this.description)
                    && timedTask.taskDateTime.equals(this.taskDateTime);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), Utils.formatDate(super.taskDateTime));
    }

    @Override
    public String toStorageString() {
        return String.format("E | %d | %s | %s", isDone ? 1 : 0, description, super.taskDateTime);
    }
}

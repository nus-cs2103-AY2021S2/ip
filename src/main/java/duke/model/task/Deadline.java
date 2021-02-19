package duke.model.task;

import static duke.commons.util.AppUtil.formatDate;
import static duke.commons.util.AppUtil.formatStorageDate;

/**
 * Represents a Deadline {@code TimedTask}.
 */
public class Deadline extends TimedTask {
    private static final String STRING_FORMAT = "[D]%s (by: %s)";
    private static final String STORAGE_STRING_FORMAT = "D | %d | %s | %s";

    /**
     * Constructor for Deadline {@code TimedTask}, specifying the description and due date.
     *
     * @param description description of the deadline
     * @param taskDateTime due date of the deadline
     */
    public Deadline(String description, String taskDateTime) {
        super(description, taskDateTime);
    }

    /**
     * Constructor for Deadline {@code TimedTask}, specifying the task's status, description and due date.
     *
     * @param done integer value to indicate the deadline's status
     * @param description description of the deadline
     * @param taskDateTime due date of the deadline
     */
    public Deadline(int done, String description, String taskDateTime) {
        super(done, description, taskDateTime);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj instanceof Deadline) {
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

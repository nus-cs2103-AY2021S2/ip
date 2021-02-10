package duke.tasks;

import duke.common.Utils;

/**
 * Represents a Deadline {@code TimedTask}.
 */
public class Deadline extends TimedTask {
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
            return timedTask.description.equalsIgnoreCase(this.description)
                    && timedTask.taskDateTime.equals(this.taskDateTime);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), Utils.formatDate(super.taskDateTime));
    }

    @Override
    public String toStorageString() {
        return String.format("D | %d | %s | %s", isDone ? 1 : 0, description, super.taskDateTime);
    }
}

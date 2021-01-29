package duke.task;

import duke.task.Task;

/**
 * Represents a to-do task.
 */
public class Todo extends Task {

    /**
     * Initialises the task with a description.
     *
     * @param description Description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Formats the task string representation.
     *
     * @return Formatted string.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}

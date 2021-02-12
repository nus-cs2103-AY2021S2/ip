package duke.task;

import duke.exception.DukeException;

/**
 * Task is a parent class of three subclasses: Todo, Event and Deadline.
 * Task has a description of the task and a boolean
 * isDone representing whether the task is done or not.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected Priority priority;

    Task(String description) {
        this.description = description;
        this.isDone = false;
        this.priority = Priority.LOW;
    }

    public boolean getIsDone() {
        return isDone;
    }

    String getPriority() {
        switch (priority) {
        case HIGH:
            return "high";
        case MEDIUM:
            return "medium";
        case LOW:
            return "low";
        default:
            return "error";
        }
    }

    String getStatusIcon() {
        return (isDone ? "\u2713" : " ");
    }

    /**
     * Changes the field isDone to true.
     */
    public void markAsDone() {
        isDone = true;
    }

    public void setPriority(String prior) throws DukeException {
        this.priority = getPriority(prior);
    }

    /**
     * Returns a string representation of the task to be stored in the
     * hard disk.
     *
     * @return A String representing the task.
     */
    String toFileString() {
        return "";
    }

    /**
     * Returns a boolean indicating whether the keyword is inside the
     * description or not.
     *
     * @param keyword The keyword to check whether it is inside the description.
     * @return A boolean value indicating whether description contains the keyword
     * or not.
     */
    boolean isKeywordInside(String keyword) {
        return description.contains(keyword);
    }

    private Priority getPriority(String p) throws DukeException {
        switch (p) {
        case "high":
            return Priority.HIGH;
        case "medium":
            return Priority.MEDIUM;
        case "low":
            return Priority.LOW;
        default:
            throw new DukeException("Priority can only be high, medium or low.");
        }
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    public int compareValue() {
        switch (priority) {
        case HIGH:
            return 1;
        case MEDIUM:
            return 2;
        case LOW:
            return 3;
        default:
            return 0;
        }
    }
}

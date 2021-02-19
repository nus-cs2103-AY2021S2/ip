import java.time.LocalDateTime;

/**
 * Represents Task object.
 */
public abstract class Task implements Comparable<Task> {
    protected String description;
    protected boolean isDone;
    protected final String ICON;
    protected final LocalDateTime TIME_CREATED;

    protected Task(String description, String icon) {
        this.description = description;
        this.ICON = icon;
        isDone = false;
        this.TIME_CREATED = LocalDateTime.now();
    }

    protected Task(String description, LocalDateTime timeCreated, String icon) {
        this.description = description;
        this.ICON = icon;
        isDone = false;
        this.TIME_CREATED = timeCreated;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getTIME_CREATED() {
        return TIME_CREATED;
    }

    public String getICON() {
        return ICON;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void changeDescription(String newDescription) {
        this.description = newDescription;
    }

    /**
     * Returns the String representation of the Task.
     *
     * @return String representation of the Task.
     */
    public String toString() {
        String doneIcon = isDone ? "[X]" : "[ ]";
        return "[" + ICON + "]" + doneIcon + " " + this.description;
    }

    /**
     * Returns the save format of the Task.
     *
     * @return Save format of the Task.
     */
    public String toLog() {
        String doneIcon = isDone ? "T" : "F";
        return ICON + " | " + doneIcon + " | " + this.description + " | " + this.TIME_CREATED.toString();
    }

    // by default compare by timeCreated
    public int compareTo(Task that) {
        return this.TIME_CREATED.compareTo(that.TIME_CREATED);
    }

    public boolean isTimed() {
        return false;
    }
}

import java.time.LocalDateTime;

public abstract class Task implements Comparable<Task> {
    protected String description;
    protected boolean isDone;
    protected final String icon;
    protected final LocalDateTime timeCreated;

    protected Task(String description, String icon) {
        this.description = description;
        this.icon = icon;
        isDone = false;
        this.timeCreated = LocalDateTime.now();
    }

    protected Task(String description, LocalDateTime timeCreated, String icon) {
        this.description = description;
        this.icon = icon;
        isDone = false;
        this.timeCreated = timeCreated;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public String getIcon() {
        return icon;
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
        return "[" + icon + "]" + doneIcon + " " + this.description + ", Created On: " + timeCreated.toString();
    }

    /**
     * Returns the save format of the Task.
     *
     * @return Save format of the Task.
     */
    public String toLog() {
        String doneIcon = isDone ? "T" : "F";
        return icon + " | " + doneIcon + " | " + this.description + " | " + this.timeCreated.toString();
    }

    // by default compare by timeCreated
    public int compareTo(Task that) {
        return this.timeCreated.compareTo(that.timeCreated);
    }

    public boolean isTimed() {
        return false;
    }
}

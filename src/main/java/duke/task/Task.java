package duke.task;

/**
 * Abstract class for Tasks.
 */
public abstract class Task {

    private final String description;
    private boolean isDone;

    /**
     * Task builder prototype.
     * Sets isDone to false by default.
     *
     * @param description Parsed string contains description of this task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getStatus() {
        return isDone;
    }

    public abstract String getSaveTime();

    public abstract String getSaveType();

    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Y is done, N is not done.
     *
     * @return String that represents the status to show for printing.
     */
    public String getStatusIcon() {
        // Tick and cross is not displayed correctly in my local environment.
        return (isDone ? "Y" : "N");
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description + ".";
    }

}

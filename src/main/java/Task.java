import java.time.LocalDate;

public class Task {

    protected final String description;
    protected final String eventDate;
    protected boolean isDone;
    protected LocalDate date;

    /**
     * Main constructor that accepts a description of the task, and by default
     * assigns a false value to the undone task.
     * @param description Description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.eventDate = "";
        this.date = null;
    }

    /**
     * Overriden method to mark a task as done.
     * @param description Description of method
     * @param eventDate Boolean flag indicating whether task is done
     */
    public Task(String description, String eventDate) {
        this.description = description;
        this.eventDate = eventDate;
        this.isDone = false;
        this.date = null;
    }

    public String getTaskType() {
        return "";
    };

    public String getStatusIcon() {
        return isDone ? "X" : "";
    }

    public String getDescription() {
        return this.description;
    }

    public String getEventDate() {
        return this.eventDate;
    }

    public void markAsDone() {
        this.isDone = true;
    }

}

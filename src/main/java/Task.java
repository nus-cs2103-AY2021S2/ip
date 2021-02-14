import java.time.LocalDate;

public class Task {

    public final String description;
    public final String eventDate;
    public boolean isDone;
    public LocalDate date;

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

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.eventDate = "";
        this.date = null;
    }

    /**
     * Overloaded method to mark a task as done.
     * @param description Description of method
     * @param eventDate Boolean flag indicating whether task is done
     */
    public Task(String description, String eventDate) {
        this.description = description;
        this.eventDate = eventDate;
        this.isDone = false;
        this.date = null;
    }

    public Task(String description, String eventDate, boolean isDone) {
        this.description = description;
        this.eventDate = eventDate;
        this.isDone = isDone;
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

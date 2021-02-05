public class Task {

    protected String description;
    protected String eventDate;
    protected boolean isDone;

    enum Level {
        low,
        average,
        high
    }


    /**
     * Main constructor that accepts a description of the task, and by default
     * assigns a false value to the undone task.
     * @param description Description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.eventDate = "";
    }

    /**
     * Overriden method to mark a task as done.
     * @param description Description of method
     * @param eventDate Boolean flag indicating whether task is done
     */
    public Task(String description, String eventDate) {
        this.description = description;
        this.eventDate = eventDate;
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

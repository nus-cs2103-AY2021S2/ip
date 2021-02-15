public class Task {
    protected String description;
    public boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        if (isDone) {
            return "[Done]";
        } else {
            return "[Incompleted]";// unsure why tick and cross did not show as intended
            // hence the change to Done and Incompleted.
        }
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String toString() {
        return this.getStatusIcon()  + this.description;
    }
}

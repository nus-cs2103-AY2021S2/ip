public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // Done tasks are crossed
    }

    public String getStatusAsNumber() {
        return (isDone ? "1" : "0"); // Done tasks are "1"
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String saveTask() {
        return " " + this.getStatusAsNumber() + " " + this.description;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}

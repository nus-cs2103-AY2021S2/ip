import java.time.LocalDate;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "1" : "0");
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String saveToFile() {
        return " | " + getStatusIcon() + " | " + getDescription();
    }

    public String toString() {
        return " | " + getStatusIcon() + " | " + getDescription();
    }
}

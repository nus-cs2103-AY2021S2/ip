import java.time.LocalDate;

public abstract class Task {
    protected String description;
    protected boolean isDone = false;

    public Task(String description) {
        this.description = description;
    }

    public abstract String getInitial();
    public abstract LocalDate getDate();

    public void finished() {
        isDone = true;
    }

    protected String statusIcon() {
        if (isDone) {
            return "[X] ";
        } else {
            return "[ ] ";
        }
    }

    public String getDone() {
        if (isDone) {
            return "1";
        }
        return "0";
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return this.statusIcon() + description;
    }
}

import java.time.LocalDate;

public abstract class Task {
    private String tag;
    private boolean complete;
    private String message;
    protected LocalDate date;

    protected Task(String tag, String message, LocalDate date) {
        this.tag = tag;
        this.complete = false;
        this.message = message;
        this.date = date;
    }

    public void setCompletion(boolean complete) {
        this.complete = complete;
    }

    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        String status = complete ? "[X] " : "[ ] ";
        return "[" + tag + "]" + status + message;
    }
}

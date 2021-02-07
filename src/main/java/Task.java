import java.time.LocalDate;

public abstract class Task {
    private String tag;
    private boolean isComplete;
    private String message;
    protected LocalDate date;

    protected Task(String tag, String message, LocalDate date) {
        this.tag = tag;
        this.isComplete = false;
        this.message = message;
        this.date = date;
    }

    public void setCompletion(boolean isComplete) {
        this.isComplete = isComplete;
    }

    public String toMemString() {
        int status = this.isComplete ? 1 : 0;
        return this.tag + " | " + status + " | " + this.message;
    }

    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        String status = this.isComplete ? "[X] " : "[ ] ";
        return "[" + this.tag + "]" + status + this.message;
    }
}

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
        //return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String timeFormat(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public abstract Task finishTask();

    public abstract String saveTask();

    @Override
    public String toString() {
        return String.format("[%s] ", this.getStatusIcon()) + this.description ;
    }
}
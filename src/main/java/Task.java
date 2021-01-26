import java.time.LocalDate;
import java.time.LocalTime;

public abstract class Task {
    protected final String description;
    protected boolean isDone;
    protected final String type;
    protected LocalDate date;
    protected LocalTime time;

    public Task(String type, boolean isDone, String description) {
        this.type = type;
        this.isDone = false;
        this.description = description;
    }

    public Task(String type, boolean isDone, String description, LocalDate date, LocalTime time) {
        this(type, isDone, description);
        this.date = date;
        this.time = time;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); //return [X] or [ ] symbols
    }

    public String getTypeIcon() {
        return "[" + type + "]";
    }


    public void setDone(boolean done) {
        isDone = done;
    }

    public boolean getIsDone() {
        return isDone;
    }

}

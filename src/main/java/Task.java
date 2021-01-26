import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    private String description;
    private boolean isDone;

    public Task(String desc) {
        this.description = desc;
        this.isDone = false;
    }

    public boolean markAsDone() {
        if(!this.isDone) {
            this.isDone = true;
            return true;
        }
        //Return boolean to signal that we have successfully / fail to mark task as done.
        return false;
    }

    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    public String format(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy Hmm"));
    }

    public String getStatusIcon() {
        return (this.isDone) ? "X" : " ";
    }
}

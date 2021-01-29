import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime date;

    public Deadline(String description, LocalDateTime date) {
        super(description);
        this.date = date;
        this.taskType = "Deadline";
    }

    @Override
    public String getTaskDate() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + getTaskDate() + ")";
    }
}

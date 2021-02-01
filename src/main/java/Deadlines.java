import java.time.LocalDateTime;
public class Deadlines extends Task{
    public String by;
    public LocalDateTime dateTime;
    //by is the time/date simple string
    public Deadlines(String description, String by) {
        super(description);
        this.by = by;
    }

    public void setDateTime(String by) {

        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "[D]" + "[" + this.getStatusIcon() + "] " + this.description + " (by: " + this.by + ")";
    }
}

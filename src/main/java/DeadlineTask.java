import java.time.LocalDateTime;

public class DeadlineTask extends Task {
    private LocalDateTime dueTime;

    public DeadlineTask(String name) {
        super(name);
    }

    public DeadlineTask(String name, String dueTime) {
        super(name);
        this.setDueTime(dueTime);
    }

    public void setDueTime(String dueTime) {
        this.dueTime = LocalDateTime.parse(dueTime);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + (this.dueTime != null ? String.format(" (by: %s)", this.dueTime) : "");
    }
}

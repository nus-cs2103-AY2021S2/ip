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

    public String getDueTime() {
        return String.format(
                "%d %s %s, %s",
                this.dueTime.getDayOfMonth(),
                this.dueTime.getMonth(),
                this.dueTime.getYear(),
                this.dueTime.toLocalTime()
        );
    }

    public String getDueTime() {
        return dueTime;
    }

    @Override
    public String toSavedString() {
        return String.format(
                "E | %d | %s | %s",
                this.getIsDone() ? 1 : 0,
                this.getName(),
                this.getDueTime()
        );
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + (this.dueTime != null ? String.format(" (by: %s)", this.getDueTime()) : "");
    }
}

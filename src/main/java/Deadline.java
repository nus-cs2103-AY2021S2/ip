import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime deadline;

    public Deadline(String desc, String deadline) {
        super(desc);
        this.deadline = ParseDateTime.parse(deadline);
    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + description
                + " (by: " + ParseDateTime.readableString(deadline) + ")";
    }
}

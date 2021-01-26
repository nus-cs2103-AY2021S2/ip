import java.time.LocalDateTime;

public class Deadline extends Task{
    private LocalDateTime deadline;

    public Deadline(String desc, LocalDateTime deadline) {
        super(desc);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), super.format(this.deadline));
    }
}

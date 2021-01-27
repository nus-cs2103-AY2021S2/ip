import java.time.LocalDateTime;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, true);
        this.by = by;
    }


    @Override
    public Deadline finishTask() {
        return new Deadline(description, by, true);
    }

    @Override
    public String saveTask() {
        return String.format("D | %s | %s | %s\n", super.getStatusIcon(),
                description, super.timeFormat(by));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + super.timeFormat(by) + ")";
    }
}
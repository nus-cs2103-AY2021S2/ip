import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends DukeTask {
    private final LocalDate deadline;

    public Deadlines(String name, LocalDate deadline) {
        super(name, TaskType.DEADLINE);
        this.deadline = deadline;
    }

    public Deadlines(String name, boolean isDone, LocalDate deadline) {
        super(name, isDone, TaskType.DEADLINE);
        this.deadline = deadline;
    }

    private String convertDate() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM uuuu");
            return this.deadline.format(formatter);
    }
    @Override
    public String toString() {
        return super.toString() + String.format(" (by: %s)", this.convertDate());
    }

    @Override
    public String formatDuke() {
        return super.formatDuke() + " | " + this.deadline;
    }

    @Override
    public DukeTask markDone() {
        return new Deadlines(this.name, true, this.deadline);
    }
}


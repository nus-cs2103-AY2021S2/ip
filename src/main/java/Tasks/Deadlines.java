package Tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadlines extends DukeTask {
    private final LocalDate deadline;

    public Deadlines(String name, String deadline) {
        super(name, TaskType.DEADLINE);
        LocalDate date = LocalDate.parse(deadline);
        this.deadline = date;
    }

    public Deadlines(String name, boolean isDone, String deadline) {
        super(name, isDone, TaskType.DEADLINE);
        LocalDate date = LocalDate.parse(deadline);
        this.deadline = date;
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
        return new Deadlines(this.name, true, this.deadline.toString());
    }
}


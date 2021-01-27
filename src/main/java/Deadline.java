import java.time.LocalDate;

class Deadline extends Task {
    protected final LocalDate date;

    public Deadline(String deadlineInfo, LocalDate deadline) {
        super(deadlineInfo);
        this.date = deadline;
    }

    public Deadline(String deadlineInfo, boolean isDone, LocalDate deadline) {
        super(deadlineInfo, isDone);
        this.date = deadline;
    }

    public String getDate() {
        return Task.printDate(this.date);
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[D][X] " + this.taskName + " (by: " + Task.printDate(date) + ")";
        } else {
            return "[D][ ] " + this.taskName + " (by: " + Task.printDate(date) + ")";
        }
    }
}
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {

    private final LocalDate deadline;

    public Deadlines(String name, LocalDate deadline) {
        super(name);
        this.deadline = deadline;
    }

    public Deadlines(String name, LocalDate deadline, boolean isDone) {
        super(name, isDone);
        this.deadline = deadline;
    }

    /**
     * Returns the deadline of the task as a LocalDate Object
     * @return LocalDate object
     */
    public LocalDate getBy() {
        return deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
